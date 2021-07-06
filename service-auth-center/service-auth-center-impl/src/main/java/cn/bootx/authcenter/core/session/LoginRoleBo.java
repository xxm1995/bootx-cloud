package cn.bootx.authcenter.core.session;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**   
* 登录用户的角色
* @author xxm  
* @date 2021/6/8 
*/
@Data
@Accessors(chain = true)
public class LoginRoleBo {
    /** 用户id */
    private Long uid;

    /** 角色 */
    private List<Long> roles;
}
