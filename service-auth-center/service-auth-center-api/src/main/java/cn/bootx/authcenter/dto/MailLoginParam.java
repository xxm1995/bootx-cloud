package cn.bootx.authcenter.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**   
* @author xxm
* @date 2020/4/27 19:14 
*/
@Data
@Accessors(chain = true)
@ApiModel("邮箱登陆参数")
public class MailLoginParam implements Serializable {

    private static final long serialVersionUID = -3947687386737717839L;
    @ApiModelProperty(value = "验证码")
    private String validationCode;

    @ApiModelProperty(value = "邮箱", required = true)
    @NotNull(message = "mail cannot be null")
    private String mail;

    @ApiModelProperty(value = "类型", required = true)
    @NotNull(message = "type cannot be null")
    private Integer type = 1;

    @ApiModelProperty(name = "client", value = "操作客户端", required = true)
    @NotNull(message = "client cannot be null")
    private String client;

}
