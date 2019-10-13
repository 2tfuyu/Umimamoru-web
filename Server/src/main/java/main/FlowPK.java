package main;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Timestamp;

@Embeddable
public class FlowPK implements Serializable {
    @ApiModelProperty(value = "ネットワークアドレス")
    int net;
    @ApiModelProperty(value = "ローカルアドレス")
    int loc;
    @ApiModelProperty(value = "計測時刻")
    Timestamp time;
}
