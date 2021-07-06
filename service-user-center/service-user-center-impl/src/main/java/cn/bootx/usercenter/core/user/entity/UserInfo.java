package cn.bootx.usercenter.core.user.entity;


import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.starter.jpa.base.JpaBaseEntity;
import cn.bootx.usercenter.core.user.convert.UserConvert;
import cn.bootx.usercenter.dto.user.UserInfoDto;
import cn.bootx.usercenter.param.user.UserInfoParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
* 用户的基本信息
* @author xxm
* @date 2020/4/24 15:21
*/

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Accessors(chain = true)
@Table(name="uc_user_info")
public class UserInfo extends JpaBaseEntity implements EntityBaseFunction<UserInfoDto> {

    /** 名称 */
    private String name;

    /** 手机号 */
    private String phone;

    /** 用户号 */
    private String code;

    /** 邮箱 */
    private String email;

    /** 注册来源 */
    private String source;

    /** 是否管理员 */
    private boolean admin;

    /** 注册时间 */
    private LocalDateTime registerTime;

    /** 用户类型 1.注册用户 2.游客 */
    private Integer type;

    /** 渠道 */
    private Long channelId;

    @Override
    public UserInfoDto toDto() {
        return UserConvert.USER_CONVERT.convert(this);
    }

    public static UserInfo init(UserInfoDto dto) {
        return UserConvert.USER_CONVERT.convert(dto);
    }

    public static UserInfo init(UserInfoParam param) {
        return UserConvert.USER_CONVERT.convert(param);
    }
}
