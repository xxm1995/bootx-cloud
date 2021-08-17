package cn.bootx.starter.auth.session.domain;


import cn.bootx.iam.core.auth.convert.AuthConvert;
import cn.bootx.iam.dto.auth.AuthInfoResult;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 登录信息值对象
 *
 * @author liuchenwei
 * @date 2018/5/4
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class LoginInfoBo implements Serializable {

    private static final long serialVersionUID = -4856174395070441971L;

    /** 用户id */
    private Long uid;
    /** 租户 */
    private Long tid;
    /** 账号 */
    private String account;
    /** 姓名 */
    private String name;
    /** 邮件 */
    private String email;
    /** 手机号 */
    private String phone;
    /** 是否超级管理员 */
    private boolean admin;

    /** 登录时间 */
    private LocalDateTime loginTime;
    /** 终端 */
    private String client;

    /**
     * 转换成
     */
    public AuthInfoResult toResult(){
        return AuthConvert.USER_CONVERT.convert(this);
    }
}
