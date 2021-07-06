package cn.bootx.usercenter.param.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**   
* @author xxm
* @date 2021/6/2 
*/
@Data
@Accessors(chain = true)
@ApiModel("用户信息参数")
public class UserInfoParam implements Serializable {
    private static final long serialVersionUID = -1263052439212534900L;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("登录账号")
    private String account;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty( "第三方登录")
    private Boolean thirdPartyLogin = false;
}
