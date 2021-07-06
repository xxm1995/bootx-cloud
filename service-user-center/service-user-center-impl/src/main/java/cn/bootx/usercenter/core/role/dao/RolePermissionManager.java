package cn.bootx.usercenter.core.role.dao;

import cn.bootx.starter.headerholder.HeaderHolder;
import cn.bootx.usercenter.core.role.entity.RolePermission;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色权限
* @author xxm
* @date 2020/5/27 16:02
*/
@Repository
@RequiredArgsConstructor
public class RolePermissionManager {

    private final RolePermissionRepository rolePermissionRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private final HeaderHolder headerHolder;

    public void deleteByRoleAndPermission(Long roleId, List<Long> permissionIds) {
        rolePermissionRepository.deleteByRoleIdAndPermissionIdInAndTid(roleId,permissionIds,headerHolder.findTid());
    }

    public void deleteByRole(Long roleId){
        rolePermissionRepository.deleteByRoleId(roleId);
    }

    public List<RolePermission> findAllByRole(Long roleId) {
        return rolePermissionRepository.findAllByRoleIdAndTid(roleId,headerHolder.findTid());
    }

    public List<RolePermission> findAllByRoles(List<Long> roleIds) {
        return rolePermissionRepository.findAllByRoleIdInAndTid(roleIds,headerHolder.findTid());

    }
}
