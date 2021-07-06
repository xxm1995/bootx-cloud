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
@ApiModel("渠道")
public class ChannelDto implements Serializable {
    private static final long serialVersionUID = 8937809944014729282L;

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("渠道类型, 0:自营 1:第三方 2:线上 3:线下")
    private int type;

    @ApiModelProperty("渠道名")
    private String name;

    @ApiModelProperty("暴露对外的渠道端类型key")
    private String key;

    @ApiModelProperty("密钥")
    private String secretKey;

    @ApiModelProperty("是否激活")
    private Boolean active;

    @ApiModelProperty("渠道端")
    private ClientDto clientDto;


}
