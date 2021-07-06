package cn.bootx.authcenter.dto;

import cn.bootx.common.web.rest.dto.BaseDto;
import cn.bootx.usercenter.dto.user.UserInfoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

import static io.swagger.annotations.ApiModelProperty.AccessMode.READ_ONLY;

/**   
* 账户
* @author xxm  
* @date 2020/4/25 17:27 
*/
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@ApiModel("用户认证")
public class UserAuthDto extends BaseDto implements Serializable {

    private static final long serialVersionUID = -131891945146840339L;

    private Long id;

    @ApiModelProperty(value = "用户主键", required = true)
    private Long uid;

    @ApiModelProperty(value = "用户登录账号", required = true)
    private String account;

    @ApiModelProperty(value = "用户密码", required = true)
    private String password;

    @ApiModelProperty(value = "注册时间", readOnly = true)
    private LocalDateTime registerTime;

    @ApiModelProperty(value = "最后登录时间", readOnly = true)
    private LocalDateTime lastLoginTime;

    @ApiModelProperty(value = "是否激活 true:激活 false:不激活,默认值为true",  accessMode = READ_ONLY)
    private boolean isActive;

    @ApiModelProperty(value = "渠道ID")
    private Long channelId;

    @ApiModelProperty(value = "authCode 自动登录使用 ",  accessMode = READ_ONLY)
    private String authCode;

    @ApiModelProperty(value = "用于标记是否是第三方 false:非第三方账号 true:第三方账号,默认值为false")
    private boolean isThirdParty;

    private UserInfoDto userInfoDto;
}
