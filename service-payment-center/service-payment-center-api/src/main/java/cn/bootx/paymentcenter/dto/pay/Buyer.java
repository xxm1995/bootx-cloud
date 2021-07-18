package cn.bootx.paymentcenter.dto.pay;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**   
* 购买人
* @author xxm  
* @date 2020/12/10 
*/
@Data
@Accessors(chain = true)
public class Buyer implements Serializable {

    private static final long serialVersionUID = 6421170311378917346L;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "邮件")
    private String email;

    @ApiModelProperty(value = "ip")
    private String ip;

    @ApiModelProperty(value = "代理")
    private String userAgent;

    @ApiModelProperty(value = "代理商平台(windows,mac,iphone,Android等)")
    private String userAgentType;

    @ApiModelProperty(value = "userClient")
    private String userClient;

}
