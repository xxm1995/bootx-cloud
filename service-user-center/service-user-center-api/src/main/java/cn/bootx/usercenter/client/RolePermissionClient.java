package cn.bootx.usercenter.client;

import java.util.List;

/**
* 角色权限关系
* @author xxm  
* @date 2020/5/27 20:43 
*/
public interface RolePermissionClient{
    /**
     * 获取单个角色的权限信息
     */
    List<Long> findPermissionIdsByRole(Long roleId);

    /**
     * 获取多个角色的权限信息
     */
    List<Long> findPermissionIdsByRoles(List<Long> roleIds);

    List<Long> findPermissionIdsByUser(Long userId);
}
