package main;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NetData")
public class NetData implements CoordinateData {
    @Id
    @ApiModelProperty(value = "ネットワークアドレス")
    int net;
    @ApiModelProperty(value = "都道府県")
    String pref;
    @ApiModelProperty(value = "ネットワークの中心緯度？ (海上通信機の座標とか)")
    double lat;
    @ApiModelProperty(value = "ネットワークの中心経度？ (海上通信機の座標とか)")
    double lng;
    @ApiModelProperty(value = "ビーチ名")
    String beach;
    @ApiModelProperty(value = "区")
    String ward;
    @ApiModelProperty(value = "市")
    String city;

    public String getCity(){
        return city;
    }

    public String getWard(){
        return ward;
    }

    public String getBeach(){
        return beach;
    }

    public String getPref(){
        return pref;
    }

    @Override
    public int getNet() {
        return net;
    }

    @Override
    public double getLatitude() {
        return lat;
    }

    @Override
    public double getLongitude() {
        return lng;
    }
}
