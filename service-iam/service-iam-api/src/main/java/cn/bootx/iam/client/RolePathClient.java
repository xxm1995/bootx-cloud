package cn.bootx.iam.client;

import java.util.List;

/**
* 角色权限关系
* @author xxm  
* @date 2020/5/27 20:43 
*/
public interface RolePathClient {

    /**
     * 根据用户id获取请求权限id(列表)
     */
    List<Long> findPathIdsByUser(Long userId);
}
