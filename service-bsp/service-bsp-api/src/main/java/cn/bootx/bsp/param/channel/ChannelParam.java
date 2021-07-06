package cn.bootx.bsp.param.channel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxm
* @date 2020/10/16
*/
@Data
@Accessors(chain = true)
@ApiModel(value = "渠道参数")
public class ChannelParam implements Serializable {

    private static final long serialVersionUID = 8977002434783372419L;
    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("渠道类型,0:自营 1:第三方 2:线上 3:线下")
    private Integer type;

    @ApiModelProperty("渠道端ID")
    private Long clientId;

    @ApiModelProperty("暴露对外的渠道端类型key")
    private String key;

    @ApiModelProperty("密钥")
    private String secretKey;
}
