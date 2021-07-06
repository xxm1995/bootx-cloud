package cn.bootx.usercenter.controller;

import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.usercenter.core.role.service.RolePermissionService;
import cn.bootx.usercenter.dto.permission.PermissionDto;
import cn.bootx.usercenter.param.role.RolePermissionParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @author xxm
* @date 2021/6/9
*/
@Api(tags = "角色权限消息")
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RolePermissionController {
    private final RolePermissionService rolePermissionService;

    @ApiOperation("保存角色授权")
    @PostMapping("/saveRolePermission")
    public ResResult<Boolean> saveRolePermission(@RequestBody RolePermissionParam param){
        rolePermissionService.saveRolePermission(param.getRoleId(),param.getPermissionIds());
        return Res.ok(true);
    }

    @ApiOperation("获取角色授权")
    @GetMapping("/findPermissionIdsByRole")
    public ResResult<List<Long>> findPermissionIdsByRole(Long roleId){
        return Res.ok(rolePermissionService.findPermissionIdsByRole(roleId));
    }

    @ApiOperation("获取角色授权(列表)")
    @GetMapping("/findPermissionIdsByRoles")
    public ResResult<List<Long>> findPermissionIdsByRoles(@RequestParam List<Long> roleIds){
        return Res.ok(rolePermissionService.findPermissionIdsByRoles(roleIds));
    }

    @ApiOperation("根据用户id获取角色授权(列表)")
    @GetMapping("/findPermissionIdsByUser")
    public ResResult<List<Long>> findPermissionIdsByUser(@RequestParam Long userId){
        return Res.ok(rolePermissionService.findPermissionIdsByUser(userId));
    }

    @ApiOperation("根据用户id获取角色授权(权限列表)")
    @GetMapping("/findPermissionsByUser")
    public ResResult<List<PermissionDto>> findPermissionsByUser(@RequestParam Long userId){
        return Res.ok(rolePermissionService.findPermissionsByUser(userId));
    }
}
