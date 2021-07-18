package cn.bootx.iam.core.auth.entity;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.iam.core.auth.convert.AuthConvert;
import cn.bootx.iam.dto.auth.AuthPasswordDto;
import cn.bootx.iam.param.auth.UserAuthParam;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 密码认证
 * 用户可以使用不同的账号和密码登录不同的租户系统。
 * @author xxm
 * @date 2020/4/24 16:15
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Accessors(chain = true)
@Table(name = "ac_auth_password")
public class AuthPassword extends JpaBaseEntity implements EntityBaseFunction<AuthPasswordDto> {

    /** 用户id */
    private Long uid;

    /** 密码 */
    private String password;

    /** 上次登录时间 */
    private LocalDateTime lastLoginTime;

    public static AuthPassword init(AuthPasswordDto dto) {
        return AuthConvert.USER_CONVERT.convert(dto);
    }
    public static AuthPassword init(UserAuthParam param) {
        return AuthConvert.USER_CONVERT.convert(param);
    }

    @Override
    public AuthPasswordDto toDto() {
        AuthPasswordDto convert = AuthConvert.USER_CONVERT.convert(this);
        convert.setPassword(null);
        return convert;
    }

}
