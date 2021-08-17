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

    private Long id;
    private String name;
    private String username;
    private String password;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;

    public UserDetail(Long id, String name, String username) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    public UserDetail(Long id, String name,String username, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.username = username;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    public UserDetail(Long id, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

}
