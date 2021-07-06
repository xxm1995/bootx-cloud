package cn.bootx.usercenter.core.role.dao;

import cn.bootx.usercenter.core.role.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**   
* 角色权限
* @author xxm  
* @date 2020/11/14 
*/
public interface RolePermissionRepository extends JpaRepository<RolePermission,Long> {

    List<RolePermission> findAllByRoleIdAndTid(Long roleId, Long tid);

    List<RolePermission> findAllByRoleIdInAndTid(List<Long> roleIds, Long tid);

    void deleteByRoleIdAndPermissionIdInAndTid(Long roleId, List<Long> permissionIds, Long tid);

    void deleteByRoleId(Long roleId);
}
