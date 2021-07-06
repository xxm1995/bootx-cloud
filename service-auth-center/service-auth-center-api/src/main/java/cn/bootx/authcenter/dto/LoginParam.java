package cn.bootx.authcenter.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 账号密码登录参数
 * @author xxm
 * @date 2020/4/25 17:45
 */
@Data
@Accessors(chain = true)
@ApiModel("账号密码")
public class LoginParam implements Serializable {
    private static final long serialVersionUID = -5790993908208076842L;

    @ApiModelProperty(value = "账号", required = true)
    @NotNull(message = "帐户不能为空")
    private String account;

    @ApiModelProperty(value = "密码", required = true,notes = "MD5加密后的密码")
    @NotNull(message = "密码不能为空")
    private String password;

    @ApiModelProperty(name = "client", value = "操作客户端", required = true)
    @NotNull(message = "客户端不能为空")
    private String client;
}
