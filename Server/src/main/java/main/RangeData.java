package main;


import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RangeData")
public class RangeData implements AnyData {
    @Id
    @ApiModelProperty(value = "ネットワークアドレス")
    int net;
    @ApiModelProperty(value = "ネットワーク内で南北の最大距離 [m]")
    int horizontal;
    @ApiModelProperty(value = "ネットワーク内で東西の最大距離 [m]")
    int vertical;

    @Override
    public int getNet() {
        return net;
    }
}
