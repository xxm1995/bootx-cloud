package cn.bootx.usercenter.client.feign;

import cn.bootx.common.web.rest.PageResult;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.common.web.rest.param.PageParam;
import cn.bootx.usercenter.code.UcErrorCodes;
import cn.bootx.usercenter.code.UserCenterCode;
import cn.bootx.usercenter.dto.role.RoleDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = UserCenterCode.APPLICATION_NAME,contextId = "roleFeign",path = "/role")
public interface RoleFeign {

    @ApiOperation(value = "添加角色（返回角色对象）")
    @ApiResponses(value = {
            @ApiResponse(code = UcErrorCodes.ROLE_ALREADY_EXISTED, message = "角色已存在"),
    })
    @PostMapping(value = "/addRole")
    ResResult<RoleDto> addRole(@RequestBody RoleDto roleDto);

    @ApiOperation(value = "删除角色")
    @ApiResponses(value = {
            @ApiResponse(code = UcErrorCodes.ROLE_NOT_EXISTED, message = "角色不存在"),
    })
    @DeleteMapping(value = "/delete")
    ResResult<Void> deleteRoleById(@RequestParam Long id);

    @ApiOperation(value = "修改角色（返回角色对象）")
    @ApiResponses(value = {
            @ApiResponse(code = UcErrorCodes.ROLE_NOT_EXISTED, message = "角色不存在"),
            @ApiResponse(code = UcErrorCodes.ROLE_ALREADY_EXISTED, message = "角色名称已存在")
    })
    @PostMapping(value = "/updateRole")
    ResResult<RoleDto> updateRole(@RequestBody RoleDto roleDto);

    @ApiOperation(value = "通过ID查询角色")
    @GetMapping(value = "/get")
    ResResult<RoleDto> findRoleById(@RequestParam Long id);

    @ApiOperation(value = "查询所有的角色")
    @GetMapping(value = "/findAll")
    ResResult<List<RoleDto>> findAll();

    @ApiOperation(value = "分页查询角色")
    @GetMapping(value = "/page")
    ResResult<PageResult<RoleDto>> page(@RequestParam PageParam pageParam);

}
