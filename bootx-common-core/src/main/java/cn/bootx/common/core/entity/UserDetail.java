package cn.bootx.common.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
* 用户信息
* @author xxm
* @date 2021/6/3
*/
@Getter
@Setter
@NoArgsConstructor
public class UserDetail implements Serializable {
    private static final long serialVersionUID = -2963943086646538532L;

    private Long id;
    private String name;
    private String username;
    private transient String password;
    /** 是否超级管理员 */
    private boolean admin;

    public UserDetail(Long id, String name, String username) {
        this.id = id;
        this.name = name;
        this.username = username;
    }

    public UserDetail(Long id, String name,String username, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.username = username;
    }
}
