package cn.bootx.iam.core.role.dao;

import cn.bootx.iam.core.role.entity.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleMenuRepository extends JpaRepository<RoleMenu,Long> {
    void deleteByRoleId(Long roleId);

    List<RoleMenu> findAllByRoleIdInAndTid(List<Long> roleIds, Long tid);

    List<RoleMenu> findAllByRoleIdAndTid(Long roleId, Long tid);
}
