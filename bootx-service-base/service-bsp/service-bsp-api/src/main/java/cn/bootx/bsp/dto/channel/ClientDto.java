package cn.bootx.bsp.dto.channel;

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
@ApiModel("渠道端")
public class ClientDto implements Serializable {

    private static final long serialVersionUID = 5121367245556614267L;

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("渠道端名")
    private String name;

    @ApiModelProperty("渠道端类型 1:线上 0:线下")
    private Integer type;

}
