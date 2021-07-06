package cn.bootx.authcenter.core.auth.entity;

import cn.bootx.authcenter.core.auth.convert.AuthConvert;
import cn.bootx.authcenter.dto.UserAuthDto;
import cn.bootx.authcenter.param.UserAuthParam;
import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 用户认证
 * 用户可以使用不同的账号和密码登录不同的租户系统。
 * @author xxm
 * @date 2020/4/24 16:15
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Accessors(chain = true)
@Table(name = "ac_user_auth")
public class UserAuth extends JpaBaseEntity implements EntityBaseFunction<UserAuthDto> {

    /** 用户id */
    private Long uid;

    /** 账号 */
    private String account;

    /** 密码 */
    private String password;

    /** 上次登录时间 */
    private LocalDateTime lastLoginTime;

    /** 注册时间 */
    private LocalDateTime registerTime;

    /** 是否可用 */
    private boolean active;

    /** 渠道ID */
    private Long channelId;

    /** 标记使用第三方登录的账号 */
    private boolean isThirdParty;


    public static UserAuth init(UserAuthDto dto) {
        return AuthConvert.USER_CONVERT.convert(dto);
    }
    public static UserAuth init(UserAuthParam param) {
        return AuthConvert.USER_CONVERT.convert(param);
    }

    @Override
    public UserAuthDto toDto() {
        UserAuthDto convert = AuthConvert.USER_CONVERT.convert(this);
        convert.setPassword(null);
        return convert;
    }

}
