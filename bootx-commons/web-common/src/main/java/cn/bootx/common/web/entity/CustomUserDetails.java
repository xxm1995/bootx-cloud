package cn.bootx.common.web.entity;

import java.io.Serializable;

/**
* 用户信息
* @author xxm
* @date 2021/6/3
*/
public class CustomUserDetails implements Serializable {
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
    /** 租户 */
    private Long tid;

    public Long getUserId() {
        return userId;
    }

    public CustomUserDetails setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getAccount() {
        return account;
    }

    public CustomUserDetails setAccount(String account) {
        this.account = account;
        return this;
    }

    public String getName() {
        return name;
    }

    public CustomUserDetails setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CustomUserDetails setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CustomUserDetails setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public boolean isAdmin() {
        return admin;
    }

    public CustomUserDetails setAdmin(boolean admin) {
        this.admin = admin;
        return this;
    }

    public Long getTid() {
        return tid;
    }

    public CustomUserDetails setTid(Long tid) {
        this.tid = tid;
        return this;
    }

    @Override
    public String toString() {
        return "CustomUserDetails{" +
                "userId=" + userId +
                ", account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", admin=" + admin +
                ", tid=" + tid +
                '}';
    }

}
