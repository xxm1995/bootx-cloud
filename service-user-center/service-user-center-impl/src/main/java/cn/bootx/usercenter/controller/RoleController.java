package cn.bootx.usercenter.controller;

import cn.bootx.common.web.rest.PageResult;
import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.common.web.rest.param.PageParam;
import cn.bootx.common.web.util.ValidationUtil;
import cn.bootx.usercenter.code.UcErrorCodes;
import cn.bootx.usercenter.core.role.service.RoleService;
import cn.bootx.usercenter.dto.role.RoleDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @author xxm
* @date 2021/6/9
*/
@Api(tags = "角色管理")
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @ApiOperation(value = "添加角色（返回角色对象）")
    @ApiResponses(value = {
            @ApiResponse(code = UcErrorCodes.ROLE_ALREADY_EXISTED, message = "角色已存在"),
    })
    @PostMapping(value = "/addRole")
    public ResResult<RoleDto> addRole(RoleDto roleDto){
        ValidationUtil.validateParam(roleDto);
        RoleDto result = roleService.addRole(roleDto);
        return Res.ok(result);
    }

    @ApiOperation(value = "删除角色")
    @ApiResponses(value = {
            @ApiResponse(code = UcErrorCodes.ROLE_NOT_EXISTED, message = "角色不存在"),
    })
    @DeleteMapping(value = "/delete")
    public ResResult<Void> deleteRoleById(Long id){
        roleService.deleteRole(id);
        return Res.ok();
    }

    @ApiOperation(value = "修改角色（返回角色对象）")
    @ApiResponses(value = {
            @ApiResponse(code = UcErrorCodes.ROLE_NOT_EXISTED, message = "角色不存在"),
            @ApiResponse(code = UcErrorCodes.ROLE_ALREADY_EXISTED, message = "角色名称已存在")
    })
    @PostMapping(value = "/updateRole")
    public ResResult<RoleDto> updateRole(@RequestBody RoleDto roleDto){
        ValidationUtil.validateParam(roleDto);
        RoleDto result = roleService.updateRole(roleDto);
        return Res.ok(result);
    }

    @ApiOperation(value = "通过ID查询角色")
    @GetMapping(value = "/get")
    public ResResult<RoleDto> findRoleById(Long id){
        return Res.ok(roleService.getById(id));
    }

    @ApiOperation(value = "查询所有的角色")
    @GetMapping(value = "/findAll")
    public ResResult<List<RoleDto>> findAll(){
        return Res.ok(roleService.findAll());
    }

    @ApiOperation(value = "分页查询角色")
    @GetMapping(value = "/page")
    public ResResult<PageResult<RoleDto>> page(PageParam pageParam){
        return Res.ok(roleService.page(pageParam));
    }

}
