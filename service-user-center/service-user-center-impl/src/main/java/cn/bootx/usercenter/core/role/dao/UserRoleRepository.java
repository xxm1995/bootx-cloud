package cn.bootx.usercenter.core.role.dao;

import cn.bootx.usercenter.core.role.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole,Long> {
    int countByRoleIdAndTid(Long roleId, Long tid);

    boolean existsByRoleIdAndTid(Long roleId, Long tid);

    void deleteByUserIdAndTid(Long userId, Long tid);

    void deleteByRoleIdAndTid(Long roleId, Long tid);

    void deleteByUserIdAndRoleIdInAndTid(Long userId, List<Long> roleIds, Long tid);

    void deleteByRoleIdAndUserIdInAndTid(Long roleId, List<Long> userIds, Long tid);

    List<UserRole> findAllByUserIdAndTid(Long userId, Long tid);

    List<UserRole> findAllByRoleIdAndTid(Long roleId, Long tid);
}
