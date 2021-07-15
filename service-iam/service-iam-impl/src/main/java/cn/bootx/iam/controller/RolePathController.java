package cn.bootx.iam.controller;

import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.iam.core.role.service.RolePathService;
import cn.bootx.iam.dto.permission.PermissionPathDto;
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

    @ApiOperation("保存请求权限关系")
    @PostMapping("/add")
    public ResResult<Boolean> add(@RequestBody RolePermissionParam param){
        rolePermissionService.add(param.getRoleId(),param.getPermissionIds());
        return Res.ok(true);
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
