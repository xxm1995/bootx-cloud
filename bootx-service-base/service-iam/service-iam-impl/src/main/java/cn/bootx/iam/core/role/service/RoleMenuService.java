package cn.bootx.iam.core.role.service;

import cn.bootx.iam.core.permission.service.PermissionMenuService;
import cn.bootx.iam.core.role.dao.RoleMenuManager;
import cn.bootx.iam.core.role.dao.RoleMenuRepository;
import cn.bootx.iam.core.role.entity.RoleMenu;
import cn.bootx.iam.dto.permission.PermissionMenuDto;
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
import static cn.bootx.iam.code.CachingCode.USER_MENU;

/**   
* 角色菜单权限
* @author xxm  
* @date 2021/7/12 
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class RoleMenuService {
    private final RoleMenuManager roleMenuManager;
    private final RoleMenuRepository roleMenuRepository;
    private final UserRoleService userRoleService;
    private final PermissionMenuService menuService;

    /**
     * 保存角色菜单授权
     */
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = {USER_MENU_ID,USER_MENU},allEntries = true)
    public void add(Long roleId, List<Long> permissionIds) {
        // 删旧增新
        roleMenuManager.deleteByRole(roleId);

        List<RoleMenu> roleMenus = permissionIds.stream()
                .map(permissionId -> new RoleMenu(roleId, permissionId))
                .collect(Collectors.toList());
        roleMenuRepository.saveAll(roleMenus);
    }

    /**
     * 根据角色查询对应的权限
     */
    public List<Long> findMenuIdsByRole(Long roleId){
        List<RoleMenu> rolePermissions = roleMenuManager.findAllByRole(roleId);
        return rolePermissions.stream()
                .map(RoleMenu::getMenuId)
                .collect(Collectors.toList());
    }

    /**
     * 根据角色查询对应的权限
     */
    public List<Long> findMenuIdsByRoles(List<Long> roleIds){
        List<RoleMenu> rolePermissions = roleMenuManager.findAllByRoles(roleIds);
        return rolePermissions.stream()
                .map(RoleMenu::getMenuId)
                .collect(Collectors.toList());
    }

    /**
     * 根据用户查询拥有的权限id
     */
    @Cacheable(value = USER_MENU_ID,key = "#userId")
    public List<Long> findMenuIdsByUser(Long userId){
        List<Long> roleIds = userRoleService.findRoleIdsByUser(userId);
        return this.findMenuIdsByRoles(roleIds);
    }

    /**
     * 查询用户查询拥有的权限信息
     */
    @Cacheable(value = USER_MENU,key = "#userId")
    public List<PermissionMenuDto> findMenusByUser(Long userId){
        List<PermissionMenuDto> menus = new ArrayList<>(0);

        List<Long> roleIds = userRoleService.findRoleIdsByUser(userId);
        if (CollUtil.isEmpty(roleIds)){
            return menus;
        }
        List<RoleMenu> RoleMenus = roleMenuManager.findAllByRoles(roleIds);
        List<Long> permissionIds = RoleMenus.stream()
                .map(RoleMenu::getMenuId)
                .distinct()
                .collect(Collectors.toList());
        if (CollUtil.isNotEmpty(permissionIds)){
            menus = menuService.findByIds(permissionIds);
        }
        return menus;
    }
}
