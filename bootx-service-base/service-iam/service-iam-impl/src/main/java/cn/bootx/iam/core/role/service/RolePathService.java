package cn.bootx.iam.core.role.service;

import cn.bootx.common.core.util.ResultConvertUtils;
import cn.bootx.iam.core.permission.service.PermissionPathService;
import cn.bootx.iam.core.role.dao.RoleManager;
import cn.bootx.iam.core.role.dao.RolePathManager;
import cn.bootx.iam.core.role.dao.RolePathRepository;
import cn.bootx.iam.core.role.entity.RolePath;
import cn.bootx.iam.dto.permission.PermissionPathDto;
import cn.bootx.iam.dto.role.RoleDto;
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

import static cn.bootx.iam.code.CachingCode.USER_PATH;
import static cn.bootx.iam.code.CachingCode.USER_PATH_ID;

/**
 * 角色路径权限关系
 * @author xxm
 * @date 2021/6/9
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RolePathService {
    private final RoleManager roleManager;
    private final RolePathManager rolePathManager;
    private final RolePathRepository rolePathRepository;

    private final PermissionPathService pathService;
    private final UserRoleService userRoleService;

    /**
     * 保存角色路径授权
     */
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = {USER_PATH,USER_PATH_ID},allEntries = true)
    public void addRolePath(Long roleId, List<Long> permissionIds) {
        // 删旧增新
        rolePathManager.deleteByRole(roleId);

        List<RolePath> rolePermissions = permissionIds.stream()
                .map(permissionId -> new RolePath(roleId, permissionId))
                .collect(Collectors.toList());
        rolePathRepository.saveAll(rolePermissions);
    }

    /**
     * 添加路径角色
     */
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = {USER_PATH,USER_PATH_ID},allEntries = true)
    public void addPathRole(Long permissionId, List<Long> roleIds){
        // 删旧增新
        rolePathManager.deleteByPath(permissionId);

        List<RolePath> rolePermissions = roleIds.stream()
                .map(roleId -> new RolePath(roleId, permissionId))
                .collect(Collectors.toList());
        rolePathRepository.saveAll(rolePermissions);
    }

    /**
     * 根据权限查询拥有的角色
     */
    public List<RoleDto> findRolesByPath(Long pathId){
        List<RolePath> rolePermissions = rolePathManager.findByPath(pathId);
        List<Long> roleIds = rolePermissions.stream()
                .map(RolePath::getRoleId)
                .collect(Collectors.toList());
        if (CollUtil.isEmpty(roleIds)){
            return new ArrayList<>();
        }
        return ResultConvertUtils.dtoListConvert(roleManager.findByIds(roleIds));
    }

    /**
     * 根据权限查询拥有的角色id
     */
    public List<Long> findRoleIdsByPath(Long pathId){
        List<RolePath> rolePermissions = rolePathManager.findByPath(pathId);
       return rolePermissions.stream()
                .map(RolePath::getRoleId)
                .collect(Collectors.toList());
    }

    /**
     * 根据角色查询对应的权限
     */
    public List<Long> findPathIdsByRole(Long roleId){
        List<RolePath> rolePermissions = rolePathManager.findByRole(roleId);
        return rolePermissions.stream()
                .map(RolePath::getPathId)
                .collect(Collectors.toList());
    }

    /**
     * 根据角色查询对应的权限
     */
    public List<Long> findPathIdsByRoles(List<Long> roleIds){
        List<RolePath> rolePermissions = rolePathManager.findByRoles(roleIds);
        return rolePermissions.stream()
                .map(RolePath::getPathId)
                .collect(Collectors.toList());
    }

    /**
     * 根据用户查询拥有的权限id
     */
    @Cacheable(value = USER_PATH_ID,key = "#userId")
    public List<Long> findPathIdsByUser(Long userId){
        List<Long> roleIds = userRoleService.findRoleIdsByUser(userId);
        if (CollUtil.isEmpty(roleIds)){
            return new ArrayList<>(1);
        }
        return this.findPathIdsByRoles(roleIds);
    }

    /**
     * 查询用户查询拥有的权限信息
     */
    @Cacheable(value = USER_PATH,key = "#userId")
    public List<PermissionPathDto> findPathsByUser(Long userId){
        List<PermissionPathDto> paths = new ArrayList<>(0);
        List<Long> roleIds = userRoleService.findRoleIdsByUser(userId);
        if (CollUtil.isEmpty(roleIds)){
            return paths;
        }
        List<RolePath> rolePaths = rolePathManager.findByRoles(roleIds);
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
