package cn.bootx.iam.param.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import static io.swagger.annotations.ApiModelProperty.AccessMode.READ_ONLY;

/**
* @author xxm
* @date 2021/6/2
*/
@Data
@Accessors(chain = true)
@ApiModel("用户认证信息参数")
public class UserAuthParam {
    private Long id;

    @ApiModelProperty(value = "用户主键", required = true)
    private Long uid;

    @ApiModelProperty(value = "用户登录账号", required = true)
    private String account;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty(value = "用户密码", required = true)
    private String password;

    @ApiModelProperty(value = "是否激活 true:激活 false:不激活,默认值为true",  accessMode = READ_ONLY)
    private boolean isActive;

    @ApiModelProperty(value = "渠道ID")
    private Long channelId;

    @ApiModelProperty(value = "用于标记是否是第三方 false:非第三方账号 true:第三方账号,默认值为false")
    private boolean thirdParty;

}
