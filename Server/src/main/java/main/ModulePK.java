package main;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ModulePK implements Serializable {
    @ApiModelProperty(value = "ネットワークアドレス")
    int net;
    @ApiModelProperty(value = "ローカルアドレス")
    int loc;
}
