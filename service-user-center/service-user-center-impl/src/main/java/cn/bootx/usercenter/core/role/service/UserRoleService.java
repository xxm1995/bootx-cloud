package cn.bootx.usercenter.core.role.service;

import cn.bootx.usercenter.core.role.dao.RoleManager;
import cn.bootx.usercenter.core.role.dao.UserRoleManager;
import cn.bootx.usercenter.core.role.entity.Role;
import cn.bootx.usercenter.core.role.entity.UserRole;
import cn.bootx.usercenter.core.user.dao.UserInfoManager;
import cn.bootx.usercenter.core.user.entity.UserInfo;
import cn.bootx.usercenter.dto.role.RoleDto;
import cn.bootx.usercenter.dto.user.UserInfoDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static cn.bootx.usercenter.code.CachingCode.USER_PERMISSION_ID;

/**
 * 用户角色关系
 * @author xxm
 * @date 2020/5/1 12:35
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserRoleService {

    private final UserRoleManager userRoleManager;
    private final UserInfoManager userInfoManager;
    private final RoleManager roleManager;

    /**
     * 给用户分配角色
     */
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = {USER_PERMISSION_ID},key = "#userId")
    public void addRoles(Long userId, List<Long> roleIds) {
        // 先删除用户拥有的角色
        userRoleManager.deleteByUser(userId);
        // 然后给用户添加角色
        userRoleManager.addRoles(userId, roleIds);
    }

    /**
     * 删除用户下指定的角色
     */
    @CacheEvict(value = {USER_PERMISSION_ID},key = "#userId")
    public void deleteByUserAndRoles(Long userId, List<Long> roleIds) {
        userRoleManager.deleteByUserAndRoles(userId, roleIds);
    }

    /**
     * 删除用户下所有角色绑定信息
     */
    @CacheEvict(value = {USER_PERMISSION_ID},key = "#userId")
    public void deleteByUser(Long userId) {
        userRoleManager.deleteByUser(userId);
    }

    /**
     * 删除角色下指定的用户
     */
    @CacheEvict(value = {USER_PERMISSION_ID},allEntries = true)
    public void deleteByRoleAndUsers(Long roleId, List<Long> userIds) {
        userRoleManager.deleteByRoleAndUsers(roleId, userIds);
    }
    /**
     * 删除角色下所有用户绑定信息
     */
    @CacheEvict(value = {USER_PERMISSION_ID},allEntries = true)
    public void deleteByRole(Long roleId) {
        userRoleManager.deleteByRole(roleId);
    }

    /**
     * 根据用户ID获取到已有角色集合
     */
    public List<RoleDto> findRolesByUser(Long userId) {
        // id集合
        List<UserRole> userRoles = userRoleManager.findAllByUser(userId);
        List<Long> ids = userRoles.stream()
                .map(UserRole::getRoleId)
                .collect(Collectors.toList());
        return roleManager.findAllByIds(ids).stream()
                .map(Role::toDto)
                .collect(Collectors.toList());
    }

    /**
     * 根据用户ID获取到已有角色id集合
     */
    public List<Long> findRoleIdsByUser(Long userId) {
        // id集合
        List<UserRole> userRoles = userRoleManager.findAllByUser(userId);
        return userRoles.stream()
                .map(UserRole::getRoleId)
                .collect(Collectors.toList());
    }

    /**
     * 根据角色ID获取到用户集合
     */
    public List<UserInfoDto> findUsersByRole(Long roleId) {
        // id集合
        List<UserRole> userRoles = userRoleManager.findAllByRole(roleId);
        List<Long> ids = userRoles.stream()
                .map(UserRole::getUserId)
                .collect(Collectors.toList());
        return userInfoManager.findAllByIds(ids).stream()
                .map(UserInfo::toDto)
                .collect(Collectors.toList());
    }

    /**
     * 根据角色ID获取到用户id集合
     */
    public List<Long> findUserIdsByRole(Long roleId) {
        // id集合
        List<UserRole> userRoles = userRoleManager.findAllByRole(roleId);
        return userRoles.stream()
                .map(UserRole::getUserId)
                .collect(Collectors.toList());
    }
}
