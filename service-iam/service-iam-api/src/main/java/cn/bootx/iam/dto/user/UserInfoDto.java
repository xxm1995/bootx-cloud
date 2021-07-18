package cn.bootx.iam.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

import static io.swagger.annotations.ApiModelProperty.AccessMode.READ_ONLY;

@Data
@Accessors(chain = true)
@ApiModel("用户信息")
public class UserInfoDto implements Serializable {
    private static final long serialVersionUID = 5881350477107722635L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("注册来源")
    private String source;

    @ApiModelProperty("是否管理员")
    private boolean admin;

    @ApiModelProperty("注册时间")
    private LocalDateTime registerTime;

    @ApiModelProperty("token")
    private String token;

    @ApiModelProperty(value = "最后登录时间", accessMode = READ_ONLY)
    private LocalDateTime lastLoginTime;

}
