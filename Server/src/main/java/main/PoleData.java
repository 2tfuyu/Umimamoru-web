package main;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import javax.persistence.*;
import java.util.List;

@Entity
public class PoleData implements CoordinateData {
    @EmbeddedId
    ModulePK pk;
    @ApiModelProperty(value = "ポールの緯度")
    double lat;
    @ApiModelProperty(value = "ポールの経度")
    double lng;

    @Override
    public int getNet() {
        return pk.net;
    }

    public int getLoc() {
        return pk.loc;
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
