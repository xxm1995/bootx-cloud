package cn.bootx.common.core.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
* 用户信息
* @author xxm
* @date 2021/6/3
*/
@Getter
@Setter
public class UserDetail implements Serializable {
    private static final long serialVersionUID = -2963943086646538532L;

    /** 用户id */
    private Long userId;
    /** 账号 */
    private String account;
    /** 名称 */
    private String name;
    /** 邮件 */
    private String email;
    /** 手机号 */
    private String phone;
    /** 是否超级管理员 */
    private boolean admin;

}
