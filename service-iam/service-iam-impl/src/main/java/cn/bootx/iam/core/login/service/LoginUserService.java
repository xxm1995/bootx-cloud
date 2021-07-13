package cn.bootx.iam.core.login.service;

import cn.bootx.common.web.exception.BizException;
import cn.bootx.iam.code.permission.PermissionCode;
import cn.bootx.iam.core.role.service.RoleMenuService;
import cn.bootx.iam.dto.auth.AuthInfoResult;
import cn.bootx.iam.dto.login.LoginMenuDto;
import cn.bootx.iam.dto.login.LoginMenuMetaDto;
import cn.bootx.iam.dto.login.LoginPermissionDto;
import cn.bootx.iam.dto.login.MenuAndPermissionDto;
import cn.bootx.iam.dto.permission.PermissionMenuDto;
import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 登录用户接口
 * @author xxm
 * @date 2021/7/12
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginUserService {

    private final RoleMenuService roleMenuService;
    private final LoginService loginService;

    /**
     * 查询用户路由菜单和按钮权限
     */
    public MenuAndPermissionDto getUserPermission(){
        AuthInfoResult userInfo = Optional.ofNullable(loginService.getUserInfo())
                .orElseThrow(() -> new BizException("未登录"));
        List<PermissionMenuDto> permissionsByUser = roleMenuService.findPermissionsByUser(userInfo.getUid());

        // 权限
        List<LoginPermissionDto> permissions = permissionsByUser.stream()
                .filter(permissionMenu -> Objects.equals(permissionMenu.getMenuType(), PermissionCode.MENU_TYPE_BUTTON))
                .map(permissionMenu -> new LoginPermissionDto()
                        .setAction(permissionMenu.getPerms())
                        .setDescribe(permissionMenu.getDescription()))
                .collect(Collectors.toList());

        // 菜单路由
        List<LoginMenuDto> routerDtos = permissionsByUser.stream()
                .filter(permissionMenu -> !Objects.equals(permissionMenu.getMenuType(), PermissionCode.MENU_TYPE_BUTTON))
                .map(this::buildMenu)
                .collect(Collectors.toList());

        // 构造路由菜单
        List<LoginMenuDto> loginMenuDtos = this.buildTreeList(routerDtos);

        return new MenuAndPermissionDto()
                .setMenus(loginMenuDtos)
                .setPermissions(permissions);

    }

    /**
     * 构建路由对象
     */
    private LoginMenuDto buildMenu(PermissionMenuDto menu){
        LoginMenuMetaDto mate = new LoginMenuMetaDto()
                .setTitle(menu.getName())
                .setComponentName(menu.getComponentName())
                .setKeepAlive(menu.isKeepAlive())
                .setIcon(menu.getIcon())
                .setRedirect(menu.getRedirect())
                .setInternalOrExternal(menu.isInternalOrExternal());

        return new LoginMenuDto()
                .setId(menu.getId())
                .setParentId(menu.getParentId())
                .setSortNo(menu.getSortNo())
                .setName(menu.getComponentName())
                .setPath(menu.getUrl())
                .setMate(mate);
    }

    /**
     * 构造部门树状结构
     */
    private List<LoginMenuDto> buildTreeList(List<LoginMenuDto> recordList) {
        // 查出没有父级的
        List<LoginMenuDto> collect = recordList.stream()
                .filter(menuDto -> Objects.isNull(menuDto.getParentId()))
                .collect(Collectors.toList());

        // 查询孩子
        for (LoginMenuDto menuDto : collect) {
            this.findChildren(menuDto,recordList);
        }
        // 排序
        collect.sort(Comparator.comparing(LoginMenuDto::getSortNo));
        return collect;
    }

    /**
     * 递归查找子节点
     */
    private void findChildren(LoginMenuDto treeNode, List<LoginMenuDto> categories) {
        for (LoginMenuDto category : categories) {
            if (treeNode.getId().equals(category.getParentId())) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                findChildren(category, categories);
                // 子节点
                treeNode.getChildren().add(category);
            }
        }
        // 排序
        if (CollUtil.isNotEmpty(treeNode.getChildren())){
            treeNode.getChildren().sort(Comparator.comparing(LoginMenuDto::getSortNo));
        }
    }
}
