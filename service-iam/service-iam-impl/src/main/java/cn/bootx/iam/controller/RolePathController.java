package cn.bootx.iam.controller;

import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.iam.core.role.service.RolePathService;
import cn.bootx.iam.dto.permission.PermissionPathDto;
import cn.bootx.iam.dto.role.RoleDto;
import cn.bootx.iam.param.role.PermissionRoleParam;
import cn.bootx.iam.param.role.RolePermissionParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @author xxm
* @date 2021/6/9
*/
@Api(tags = "角色请求权限消息关系")
@RestController
@RequestMapping("/role/path")
@RequiredArgsConstructor
public class RolePathController {
    private final RolePathService rolePermissionService;

    @ApiOperation("保存角色权限关联关系")
    @PostMapping("/addRolePath")
    public ResResult<Boolean> addRolePath(@RequestBody RolePermissionParam param){
        rolePermissionService.addRolePath(param.getRoleId(),param.getPermissionIds());
        return Res.ok(true);
    }

    @ApiOperation("保存权限角色关联关系")
    @PostMapping("/addPathRole")
    public ResResult<Boolean> addPathRole(@RequestBody PermissionRoleParam param){
        rolePermissionService.addPathRole(param.getPermissionId(),param.getRoleIds());
        return Res.ok(true);
    }

    @ApiOperation("根据权限查询拥有的角色")
    @GetMapping("/findRoleByPath")
    public ResResult<List<RoleDto>> findRoleByPath(Long pathId){
        return Res.ok(rolePermissionService.findRoleByPath(pathId));
    }

    @ApiOperation("根据角色id获取请求权限id")
    @GetMapping("/findIdsByRole")
    public ResResult<List<Long>> findIdsByRole(Long roleId){
        return Res.ok(rolePermissionService.findIdsByRole(roleId));
    }

    @ApiOperation("根据角色ids获取请求权限id")
    @GetMapping("/findIdsByRoles")
    public ResResult<List<Long>> findIdsByRoles(@RequestParam List<Long> roleIds){
        return Res.ok(rolePermissionService.findIdsByRoles(roleIds));
    }

    @ApiOperation("根据用户id获取请求权限id(列表)")
    @GetMapping("/findPermissionIdsByUser")
    public ResResult<List<Long>> findPermissionIdsByUser(@RequestParam Long userId){
        return Res.ok(rolePermissionService.findPermissionIdsByUser(userId));
    }

    @ApiOperation("根据用户id获取角色授权(权限列表)")
    @GetMapping("/findPermissionsByUser")
    public ResResult<List<PermissionPathDto>> findPermissionsByUser(@RequestParam Long userId){
        return Res.ok(rolePermissionService.findByUser(userId));
    }
}
