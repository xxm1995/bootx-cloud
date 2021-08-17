package cn.bootx.iam.controller;

import cn.bootx.common.core.rest.Res;
import cn.bootx.common.core.rest.ResResult;
import cn.bootx.iam.core.upms.service.RoleMenuService;
import cn.bootx.iam.dto.permission.PermissionMenuDto;
import cn.bootx.iam.param.upms.RolePermissionParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**   
* 角色权限(菜单)关联关系
* @author xxm  
* @date 2021/7/12 
*/
@Api(tags = "角色菜单权限关系")
@RestController
@RequestMapping("/role/menu")
@RequiredArgsConstructor
public class RoleMenuController {
    private final RoleMenuService roleMenuService;

    @ApiOperation("保存请求权限关系")
    @PostMapping("/add")
    public ResResult<Boolean> add(@RequestBody RolePermissionParam param){
        roleMenuService.add(param.getRoleId(),param.getPermissionIds());
        return Res.ok(true);
    }

    @ApiOperation("根据角色id获取请求权限id")
    @GetMapping("/findMenuIdsByRole")
    public ResResult<List<Long>> findMenuIdsByRole(Long roleId){
        return Res.ok(roleMenuService.findMenuIdsByRole(roleId));
    }

    @ApiOperation("根据角色ids获取请求权限id")
    @GetMapping("/findMenuIdsByRoles")
    public ResResult<List<Long>> findMenuIdsByRoles(@RequestParam List<Long> roleIds){
        return Res.ok(roleMenuService.findMenuIdsByRoles(roleIds));
    }

    @ApiOperation("根据用户id获取请求权限id(列表)")
    @GetMapping("/findMenuIdsByUser")
    public ResResult<List<Long>> findMenuIdsByUser(@RequestParam Long userId){
        return Res.ok(roleMenuService.findMenuIdsByUser(userId));
    }

    @ApiOperation("根据用户id获取角色授权(权限列表)")
    @GetMapping("/findMenusByUser")
    public ResResult<List<PermissionMenuDto>> findMenusByUser(@RequestParam Long userId){
        return Res.ok(roleMenuService.findMenusByUser(userId));
    }
}
