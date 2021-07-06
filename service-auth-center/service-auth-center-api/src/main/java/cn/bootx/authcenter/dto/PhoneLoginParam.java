package cn.bootx.authcenter.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**   
* @author xxm
* @date 2020/4/27 19:08 
*/
@Data
@Accessors(chain = true)
@ApiModel("手机号登陆参数")
public class PhoneLoginParam implements Serializable {

    private static final long serialVersionUID = -6070720717365426092L;
    @ApiModelProperty(value = "验证码")
    private String validationCode;

    @ApiModelProperty(value = "手机号", required = true)
    @NotNull(message = "phone cannot be null")
    private String phone;

    @ApiModelProperty(value = "类型")
    @NotNull(message = "type cannot be null")
    private Integer type = 1;

    @ApiModelProperty(name = "client", value = "操作客户端", required = true)
    @NotNull(message = "client cannot be null")
    private String client;

}
