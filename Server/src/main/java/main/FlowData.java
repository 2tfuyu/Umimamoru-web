package main;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "FlowData")
public class FlowData implements AnyData {
    public double tobank;
    @EmbeddedId
    FlowPK pk;
    @ApiModelProperty(value = "流速")
    double flow;
    @ApiModelProperty(value = "流向")
    double direction;
    @ApiModelProperty(value = "角度")
    double angle;

    public double getAngle(){
        return angle;
    }

    public double getTobank(){
        return tobank;
    }

    public double getFlow(){
        return this.flow;
    }

    public double getDirection(){
        return this.direction;
    }

    @Override
    public int getNet() {
        return pk.net;
    }

    public int getLoc() {
        return pk.loc;
    }

    public Timestamp getTime() {
        return pk.time;
    }
}
