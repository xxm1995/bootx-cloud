package cn.bootx.usercenter.core.role.service;

import cn.bootx.usercenter.core.permission.service.PermissionService;
import cn.bootx.usercenter.core.role.dao.RolePermissionManager;
import cn.bootx.usercenter.core.role.dao.RolePermissionRepository;
import cn.bootx.usercenter.core.role.entity.RolePermission;
import cn.bootx.usercenter.dto.permission.PermissionDto;
import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static cn.bootx.usercenter.code.CachingCode.USER_PERMISSION;
import static cn.bootx.usercenter.code.CachingCode.USER_PERMISSION_ID;

/**
* 角色权限关系
* @author xxm  
* @date 2021/6/9 
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class RolePermissionService {
    private final RolePermissionManager rolePermissionManager;
    private final RolePermissionRepository rolePermissionRepository;

    private final PermissionService permissionService;
    private final UserRoleService userRoleService;
    /**
     * 保存角色授权
     */
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = {USER_PERMISSION_ID,USER_PERMISSION},allEntries = true)
    public void saveRolePermission(Long roleId,List<Long> permissionIds) {
        // 删旧增新
        rolePermissionManager.deleteByRole(roleId);

        List<RolePermission> rolePermissions = permissionIds.stream()
                .map(permissionId -> new RolePermission(roleId, permissionId))
                .collect(Collectors.toList());
        rolePermissionRepository.saveAll(rolePermissions);
    }

    /**
     * 根据角色查询对应的权限
     */
    public List<Long> findPermissionIdsByRole(Long roleId){
        List<RolePermission> rolePermissions = rolePermissionManager.findAllByRole(roleId);
        return rolePermissions.stream()
                .map(RolePermission::getPermissionId)
                .collect(Collectors.toList());
    }

    /**
     * 根据角色查询对应的权限
     */
    public List<Long> findPermissionIdsByRoles(List<Long> roleIds){
        List<RolePermission> rolePermissions = rolePermissionManager.findAllByRoles(roleIds);
        return rolePermissions.stream()
                .map(RolePermission::getPermissionId)
                .collect(Collectors.toList());
    }

    /**
     * 根据用户查询拥有的权限id
     */
    @Cacheable(value = USER_PERMISSION_ID,key = "#userId")
    public List<Long> findPermissionIdsByUser(Long userId){
        List<Long> roleIds = userRoleService.findRoleIdsByUser(userId);
        return this.findPermissionIdsByRoles(roleIds);
    }

    /**
     * 查询用户查询拥有的权限信息
     */
    @Cacheable(value = USER_PERMISSION,key = "#userId")
    public List<PermissionDto> findPermissionsByUser(Long userId){
        List<Long> roleIds = userRoleService.findRoleIdsByUser(userId);
        List<RolePermission> rolePermissions = rolePermissionManager.findAllByRoles(roleIds);
        List<Long> permissionIds = rolePermissions.stream()
                .map(RolePermission::getPermissionId)
                .distinct()
                .collect(Collectors.toList());
        List<PermissionDto> permissionDtos = new ArrayList<>(0);
        if (CollUtil.isNotEmpty(permissionIds)){
            permissionDtos = permissionService.findByIds(permissionIds);
        }
        return permissionDtos;
    }
}
