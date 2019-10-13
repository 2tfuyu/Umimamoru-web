package main;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/umimamoru")
public class MainController {

    private static final double GRS80_A = 6378137.000;//長半径 a(m)
    private static final double GRS80_E2 = 0.00669438002301188;//第一遠心率  eの2乗

    public static double deg2rad(double deg) {
        return deg * Math.PI / 180.0;
    }

    public static double rad2(double rad) {
        return rad * 180.0 / Math.PI;
    }

    public static double average(double... data) {
        return Arrays.stream(data).sum() / data.length;
    }

    public static double distance(double a, double b) {
        return Math.abs(a - b);
    }

    public static double toRad(double a) {
        return a * Math.PI / 180;
    }

    public static double w(double latitudeAverage) {
        return Math.sqrt(1.0 - GRS80_E2 * Math.pow(Math.sin(latitudeAverage), 2));
    }

    public static double n(double w) {
        return GRS80_A / w;
    }

    public static double latitudeDistance(double a, double b) {
        return toRad(distance(a, b)) * GRS80_A * (1 - GRS80_E2) / Math.pow(w(average(a, b)), 3);
    }

    public static double longitudeDistance(double latA, double latB, double lngA, double lngB) {
        return toRad(distance(lngA, lngB)) * Math.cos(distance(latA, latB)) * GRS80_A / w(average(latA, latB));
    }

    @Autowired
    private RangeRepository rrep;
    @Autowired
    private FlowRepository frep;
    @Autowired
    private ModuleRepository mrep;
    @Autowired
    private PoleRepository prep;
    @Autowired
    private NetRepository nrep;

    private static <T> T getMax(List<T> list, Comparator<? super T> comparator) {
        return list
                .stream()
                .max(comparator)
                .orElse(null);
    }

    private static <T> T getMin(List<T> list, Comparator<? super T> comparator) {
        return list
                .stream()
                .min(comparator)
                .orElse(null);
    }

    @ApiOperation(value = "ネットワークアドレスから対応するネットワークのデータを取得する(リスト)")
    @RequestMapping(method = RequestMethod.GET, value = "net")
    public NetData[] getNet(@RequestParam("net") int net) {
        return nrep.findByNet(net).toArray(new NetData[0]);
    }

    @ApiOperation(value = "県名から対応するネットワークのデータを取得する(リスト)")
    @RequestMapping(method = RequestMethod.GET, value = "net/pref")
    public NetData[] getNetPref(@RequestParam("pref") String pref) {
        return nrep.findByPref(pref).toArray(new NetData[0]);
    }

    @ApiOperation(value = "ビーチ名から対応するネットワークのデータを取得する(リスト)")
    @RequestMapping(method = RequestMethod.GET, value = "net/beach")
    public NetData[] getNetBeach(@RequestParam("beach") String beach) {
        return nrep.findByBeach(beach).toArray(new NetData[0]);
    }


    @ApiOperation(value = "ネットワークアドレスから対応するモジュールのデータを取得する(リスト)")
    @RequestMapping(method = RequestMethod.GET, value = "net/module")
    public ModuleData[] getNetModule(@RequestParam("net") int net) {
        return mrep.findByPk_Net(net).toArray(new ModuleData[0]);
    }

    @ApiOperation(value = "ネットワークアドレスから対応するポールのデータを取得する(リスト)")
    @RequestMapping(method = RequestMethod.GET, value = "net/pole")
    public PoleData[] getNetPole(@RequestParam("net") int net) {
        return prep.findByPk_Net(net).toArray(new PoleData[0]);
    }

    @ApiOperation(value = "ネットワークアドレス、モジュールのローカルアドレスから対応するポールのデータを取得する(リスト)")
    @RequestMapping(method = RequestMethod.GET, value = "pole")
    public PoleData[] getPole(@RequestParam("net") int net, @RequestParam("loc") int loc) {
        ModuleData[] array = getNetModule(net);
        for (ModuleData module : array) {
            if (module.pk.loc == loc) return getNetPole(module.pole);
        }
        return new PoleData[0];
    }

    @ApiOperation(value = "ネットワークアドレスからそのネットワーク内の最大東西・南北距離を求める [m]")
    @RequestMapping(method = RequestMethod.GET, value = "net/range")
    public Map<String, Double> getDrawRange(@RequestParam("net") int net) {
        Map<String, Double> result = new HashMap<>();
        List<ModuleData> modules = mrep.findByPk_Net(net);
        List<PoleData> poles = prep.findByPk_Net(net);
        double maxLat, minLat, maxLng, minLng;
        minLat = getMin(modules, Comparator.comparingDouble(ModuleData::getLatitude)).getLatitude();
        maxLat = getMax(modules, Comparator.comparingDouble(ModuleData::getLatitude)).getLatitude();
        minLng = getMin(modules, Comparator.comparingDouble(ModuleData::getLongitude)).getLongitude();
        maxLng = getMax(modules, Comparator.comparingDouble(ModuleData::getLongitude)).getLongitude();
        result.put("vertical", latitudeDistance(maxLat, minLat));
        result.put("horizontal", longitudeDistance(maxLat, minLat, maxLng, minLng));
        return result;
    }

    @ApiOperation(value = "ネットワークアドレスから対応するモジュールの波データを取得する (リスト, モジュール内で重複があった場合は最新のみ)")
    @RequestMapping(method = RequestMethod.GET, value = "net/flow")
    public List<Flows> getNetFlow(@RequestParam("net") int net) {
        List<FlowData> flow = frep.findByPk_Net(net);
        return pickNew(flow);
    }

    @ApiOperation(value = "ネットワークアドレスから対応するポールの波データを取得する (リスト, モジュール内で重複があった場合は最新のみ)")
    @RequestMapping(method = RequestMethod.GET, value = "pole/flow")
    public List<Flows> getPoleFlow(@RequestParam("net") int net, @RequestParam("loc") int loc) {
        List<Integer> modules = new ArrayList<>();
        for (ModuleData mod : getNetModule(net)) {
            if (mod.pole == loc) {
                modules.add(mod.getLoc());
            }
        }
        List<FlowData> flow = frep.findByPk_Net(net);
        return pickNew(flow.stream().filter(flowData -> modules.contains(flowData.getLoc())).collect(Collectors.toList()));
    }

    public List<Flows> pickNew(List<FlowData> flow) {
        Map<Integer, List<FlowData>> flows = new HashMap<>();
        for (FlowData f : flow) {
            if (Objects.isNull(f)) continue;
            if (Objects.isNull(flows.get(f.pk.loc))) flows.put(f.pk.loc, new ArrayList<>());
            List<FlowData> list = flows.get(f.pk.loc);
            list.add(f);
        }

        List<Flows> list = new ArrayList<>();
        for (int key : flows.keySet()) {
            List<FlowData> array = flows.get(key);
            array.sort(Comparator.comparing(FlowData::getTime));
            boolean before = false;
            int count = 0;
            for (FlowData f : array) {
                if (!before && f.getFlow() >= 1.6 && (f.angle > Math.PI * 3 / 4 && f.angle < Math.PI * 5 / 4)) {
                    before = true;
                    count++;
                } else if (before && f.getTobank() < 1.6 || (f.angle > Math.PI * 3 / 4 && f.angle < Math.PI * 5 / 4)) {
                    before = false;
                }
            }
            list.add(new Flows(array.get(array.size() - 1), count));
        }

        return list;
    }

    private double getAngle(FlowData flow, ModuleData module) {
        return Math.max(flow.getDirection(), module.angle) - Math.min(flow.getDirection(), module.angle);
    }

    private double getFlowToBank(FlowData flow, ModuleData module) {
        return flow.getFlow() * Math.cos(getAngle(flow, module));
    }

    @RequestMapping(method = RequestMethod.POST, value = "config/module")
    public void setModule(@RequestParam("net") int mac,
                          @RequestParam("loc") int ip,
                          @RequestParam("lat") double lat,
                          @RequestParam("lng") double lng,
                          @RequestParam("pole") int pole) {
        ModuleData module = new ModuleData();
        module.pk = new ModulePK();
        module.pk.net = mac;
        module.pk.loc = ip;
        module.pole = pole;
        module.lat = lat;
        module.lng = lng;
        module.angle = getAngle(module, prep.findByPk_NetAndPk_Loc(module.getNet(), module.getPole()).get(0));
        module.angle = module.angle < 0 ? 2 * Math.PI + module.angle : module.angle;
        mrep.save(module);
    }

    private double getAngle(ModuleData module, PoleData pole) {
        double vertical = latitudeDistance(module.lat, pole.lat);
        double horizontal = longitudeDistance(module.lat, pole.lat, module.lng, pole.lng);
        return Math.atan(horizontal / vertical);
    }

    @RequestMapping(method = RequestMethod.POST, value = "config/pole")
    public void setPole(@RequestParam("mac") int mac,
                        @RequestParam("ip") int ip,
                        @RequestParam("lat") double lat,
                        @RequestParam("lng") double lng) {
        PoleData pole = new PoleData();
        pole.pk.net = mac;
        pole.pk.loc = ip;
        pole.lat = lat;
        pole.lng = lng;
        prep.save(pole);
    }


    @RequestMapping(method = RequestMethod.POST, value = "config/net")
    public void setNet(@RequestParam("net") int net,
                       @RequestParam("lat") double lat,
                       @RequestParam("lng") double lng) {
        NetData net_ = new NetData();
        net_.net = net;
        net_.lat = lat;
        net_.lng = lng;
        nrep.save(net_);
    }

    @RequestMapping(method = RequestMethod.POST, value = "config/flow")
    public void setFlow(@RequestParam("net") int net,
                        @RequestParam("loc") int loc,
                        @RequestParam("time") String time,
                        @RequestParam("flow") double flow,
                        @RequestParam("direction") double direction) {
        FlowData flow_ = new FlowData();
        flow_.pk = new FlowPK();
        flow_.pk.net = net;
        flow_.pk.loc = loc;
        try {
            flow_.pk.time = new Timestamp(
                    (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"))
                            .parse(time).getTime()
            );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        flow_.flow = flow;
        flow_.direction = direction < 0 ? 2 * Math.PI + direction : direction;
        ModuleData data = mrep.findByPk_NetAndPk_Loc(net, loc).get(0);
        flow_.angle = getAngle(flow_, data);
        flow_.tobank = getFlowToBank(flow_, data);
        frep.save(flow_);
    }
}
