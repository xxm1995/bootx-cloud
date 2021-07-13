package cn.bootx.iam.core.role.service;

import cn.bootx.iam.core.permission.service.PermissionPathService;
import cn.bootx.iam.core.role.dao.RolePathManager;
import cn.bootx.iam.core.role.dao.RolePathRepository;
import cn.bootx.iam.core.role.entity.RolePath;
import cn.bootx.iam.dto.permission.PermissionPathDto;
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

import static cn.bootx.iam.code.CachingCode.*;

/**
* 角色路径权限关系
* @author xxm  
* @date 2021/6/9 
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class RolePathService {
    private final RolePathManager rolePathManager;
    private final RolePathRepository rolePathRepository;

    private final PermissionPathService pathService;
    private final UserRoleService userRoleService;

    /**
     * 保存角色路径授权
     */
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = {USER_PATH,USER_PATH_ID},allEntries = true)
    public void add(Long roleId, List<Long> permissionIds) {
        // 删旧增新
        rolePathManager.deleteByRole(roleId);

        List<RolePath> rolePermissions = permissionIds.stream()
                .map(permissionId -> new RolePath(roleId, permissionId))
                .collect(Collectors.toList());
        rolePathRepository.saveAll(rolePermissions);
    }

    /**
     * 根据角色查询对应的权限
     */
    public List<Long> findIdsByRole(Long roleId){
        List<RolePath> rolePermissions = rolePathManager.findAllByRole(roleId);
        return rolePermissions.stream()
                .map(RolePath::getPathId)
                .collect(Collectors.toList());
    }

    /**
     * 根据角色查询对应的权限
     */
    public List<Long> findIdsByRoles(List<Long> roleIds){
        List<RolePath> rolePermissions = rolePathManager.findAllByRoles(roleIds);
        return rolePermissions.stream()
                .map(RolePath::getPathId)
                .collect(Collectors.toList());
    }

    /**
     * 根据用户查询拥有的权限id
     */
    @Cacheable(value = USER_PATH_ID,key = "#userId")
    public List<Long> findPermissionIdsByUser(Long userId){
        List<Long> roleIds = userRoleService.findRoleIdsByUser(userId);
        return this.findIdsByRoles(roleIds);
    }

    /**
     * 查询用户查询拥有的权限信息
     */
    @Cacheable(value = USER_PATH,key = "#userId")
    public List<PermissionPathDto> findByUser(Long userId){
        List<PermissionPathDto> paths = new ArrayList<>(0);
        List<Long> roleIds = userRoleService.findRoleIdsByUser(userId);
        if (CollUtil.isEmpty(roleIds)){
            return paths;
        }
        List<RolePath> rolePaths = rolePathManager.findAllByRoles(roleIds);
        List<Long> pathIds = rolePaths.stream()
                .map(RolePath::getPathId)
                .distinct()
                .collect(Collectors.toList());
        if (CollUtil.isNotEmpty(pathIds)){
            paths = pathService.findByIds(pathIds);
        }
        return paths;
    }
}
