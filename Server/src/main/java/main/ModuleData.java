package main;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ModuleData")
public class ModuleData implements CoordinateData {
    @EmbeddedId
    ModulePK pk;
    @ApiModelProperty(value = "緯度")
    double lat;
    @ApiModelProperty(value = "経度")
    double lng;
    @ApiModelProperty(value = "対応するポール")
    int pole;

    double angle;

    @Override
    public int getNet() {
        return pk.net;
    }

    public int getPole(){
        return pole;
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
