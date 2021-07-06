package cn.bootx.usercenter.core.role.dao;

import cn.bootx.starter.headerholder.HeaderHolder;
import cn.bootx.usercenter.core.role.entity.UserRole;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
* 用户角色关系
* @author xxm
* @date 2020/5/1 11:23
*/
@Repository
@RequiredArgsConstructor
public class UserRoleManager {
    private final UserRoleRepository userRoleRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private final HeaderHolder headerHolder;

    /**
     * 添加用户角色关系
     */
    public void addRoles(Long id, List<Long> roleIds) {
        List<UserRole> collect = roleIds.stream()
                .map(roleId -> new UserRole().setRoleId(roleId).setUserId(id))
                .collect(Collectors.toList());

        userRoleRepository.saveAll(collect);
    }

    public int countByRoleId(Long roleId) {
        return userRoleRepository.countByRoleIdAndTid(roleId,headerHolder.findTid());
    }

    public boolean existsByRoleId(Long roleId) {
        return userRoleRepository.existsByRoleIdAndTid(roleId,headerHolder.findTid());
    }

    public void deleteByUser(Long userId) {
        userRoleRepository.deleteByUserIdAndTid(userId,headerHolder.findTid());
    }

    public void deleteByRole(Long roleId) {
        userRoleRepository.deleteByRoleIdAndTid(roleId,headerHolder.findTid());
    }

    /**
     * 删除用户的指定角色
     */
    public void deleteByUserAndRoles(Long userId, List<Long> roleIds) {
        userRoleRepository.deleteByUserIdAndRoleIdInAndTid(userId,roleIds,headerHolder.findTid());
    }

    /**
     * 删除角色的指定用户
     */
    public void deleteByRoleAndUsers(Long roleId, List<Long> userIds) {
        userRoleRepository.deleteByRoleIdAndUserIdInAndTid(roleId,userIds,headerHolder.findTid());
    }

    public List<UserRole> findAllByUser(Long userId) {
        return userRoleRepository.findAllByUserIdAndTid(userId,headerHolder.findTid());
    }

    public List<UserRole> findAllByRole(Long roleId) {
        return userRoleRepository.findAllByRoleIdAndTid(roleId,headerHolder.findTid());
    }
}
