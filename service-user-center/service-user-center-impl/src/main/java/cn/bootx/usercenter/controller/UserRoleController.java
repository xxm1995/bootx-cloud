package cn.bootx.usercenter.controller;

import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.common.web.util.ValidationUtil;
import cn.bootx.usercenter.core.role.service.UserRoleService;
import cn.bootx.usercenter.dto.role.RoleDto;
import cn.bootx.usercenter.dto.user.UserInfoDto;
import cn.bootx.usercenter.param.role.RoleUsersParam;
import cn.bootx.usercenter.param.role.UserRoleParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @author xxm
* @date 2020/5/1 18:09
*/
@Api(tags = "用户角色管理")
@RestController
@RequestMapping("/role")
@AllArgsConstructor
public class UserRoleController {

    private final UserRoleService userRoleService;

    @ApiOperation(value = "给用户分配角色")
    @PostMapping(value = "/addUserRole")
    public ResResult<Boolean> addUserRole(@RequestBody UserRoleParam param) {

        ValidationUtil.validateParam(param);
        userRoleService.addRoles(param.getUserId(), param.getRoleIds());
        return Res.ok(true);
    }

    @ApiOperation(value = "给用户删除角色")
    @DeleteMapping(value = "/removeRolesByUser")
    public ResResult<Boolean> removeRolesByUser(@RequestBody UserRoleParam param) {
        ValidationUtil.validateParam(param);
        userRoleService.deleteByUserAndRoles(param.getUserId(), param.getRoleIds());
        return Res.ok(true);
    }

    @ApiOperation(value = "删除用户下所有角色绑定信息")
    @DeleteMapping(value = "/removeAllRolesByUserId")
    public ResResult<Boolean> removeAllRolesByUserId(Long id) {
        userRoleService.deleteByUser(id);
        return Res.ok(true);
    }

    @ApiOperation(value = "给角色删除用户")
    @DeleteMapping(value = "/removeUserRolesByRole")
    public ResResult<Boolean> removeUserRolesByRole(@RequestBody RoleUsersParam param) {
        ValidationUtil.validateParam(param);
        userRoleService.deleteByRoleAndUsers(param.getRoleId(), param.getUserIds());
        return Res.ok(true);
    }

    @ApiOperation(value = "删除用户下所有角色绑定信息")
    @DeleteMapping(value = "/removeAllUserRolesByRole")
    public ResResult<Boolean> removeAllUserRolesByRole(Long id) {
        userRoleService.deleteByRole(id);
        return Res.ok(true);
    }

    @ApiOperation(value = "根据用户ID获取到角色集合")
    @GetMapping(value = "/findRolesByUser")
    public ResResult<List<RoleDto>> findRolesByUser(Long id) {
        return Res.ok(userRoleService.findRolesByUser(id));
    }

    @ApiOperation(value = "根据用户ID获取到角色id集合")
    @GetMapping(value = "/findRoleIdsByUser")
    public ResResult<List<Long>> findRoleIdsByUser(Long id) {
        return Res.ok(userRoleService.findRoleIdsByUser(id));
    }

    @ApiOperation(value = "根据角色ID获取到用户集合")
    @GetMapping(value = "/findUsersByRole")
    public ResResult<List<UserInfoDto>> findUsersByRole(Long id) {
        return Res.ok(userRoleService.findUsersByRole(id));
    }

    @ApiOperation(value = "根据角色ID获取到用户集合")
    @GetMapping(value = "/findUserIdsByRole")
    public ResResult<List<Long>> findUserIdsByRole(Long id) {
        return Res.ok(userRoleService.findUserIdsByRole(id));
    }

}
