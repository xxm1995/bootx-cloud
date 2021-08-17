package cn.bootx.iam.core.role.dao;

import cn.bootx.iam.core.role.entity.RoleMenu;
import cn.bootx.common.headerholder.HeaderHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* 角色菜单权限
* @author xxm
* @date 2021/7/12
*/
@Slf4j
@Repository
@RequiredArgsConstructor
public class RoleMenuManager {
    private final RoleMenuRepository repository;
    private final HeaderHolder headerHolder;

    public void deleteByRole(Long roleId) {
        repository.deleteByRoleId(roleId);
    }


    public List<RoleMenu> findAllByRole(Long roleId) {
        return repository.findAllByRoleIdAndTid(roleId,headerHolder.findTid());
    }

    public List<RoleMenu> findAllByRoles(List<Long> roleIds) {
        return repository.findAllByRoleIdInAndTid(roleIds,headerHolder.findTid());
    }
}
