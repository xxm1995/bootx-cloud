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
    private final RolePathService rolePathService;

    @ApiOperation("保存角色权限关联关系")
    @PostMapping("/addRolePath")
    public ResResult<Boolean> addRolePath(@RequestBody RolePermissionParam param){
        rolePathService.addRolePath(param.getRoleId(),param.getPermissionIds());
        return Res.ok(true);
    }

    @ApiOperation("保存权限角色关联关系")
    @PostMapping("/addPathRole")
    public ResResult<Boolean> addPathRole(@RequestBody PermissionRoleParam param){
        rolePathService.addPathRole(param.getPermissionId(),param.getRoleIds());
        return Res.ok(true);
    }

    @ApiOperation("根据权限查询拥有的角色")
    @GetMapping("/findRolesByPath")
    public ResResult<List<RoleDto>> findRolesByPath(Long pathId){
        return Res.ok(rolePathService.findRolesByPath(pathId));
    }

    @ApiOperation("根据权限查询拥有的角色id")
    @GetMapping("/findRoleIdsByPath")
    public ResResult<List<Long>> findRoleIdsByPath(Long pathId){
        return Res.ok(rolePathService.findRoleIdsByPath(pathId));
    }

    @ApiOperation("根据角色id获取请求权限id")
    @GetMapping("/findPathIdsByRole")
    public ResResult<List<Long>> findPathIdsByRole(Long roleId){
        return Res.ok(rolePathService.findPathIdsByRole(roleId));
    }

    @ApiOperation("根据角色ids获取请求权限id")
    @GetMapping("/findPathIdsByRoles")
    public ResResult<List<Long>> findPathIdsByRoles(@RequestParam List<Long> roleIds){
        return Res.ok(rolePathService.findPathIdsByRoles(roleIds));
    }

    @ApiOperation("根据用户id获取请求权限id(列表)")
    @GetMapping("/findPathIdsByUser")
    public ResResult<List<Long>> findPathIdsByUser(@RequestParam Long userId){
        return Res.ok(rolePathService.findPathIdsByUser(userId));
    }

    @ApiOperation("根据用户id获取角色授权(权限列表)")
    @GetMapping("/findPathsByUser")
    public ResResult<List<PermissionPathDto>> findPathsByUser(@RequestParam Long userId){
        return Res.ok(rolePathService.findPathsByUser(userId));
    }
}
