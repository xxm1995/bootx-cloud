package cn.bootx.usercenter.client.feign;

import cn.bootx.common.web.rest.ResResult;
import cn.bootx.usercenter.code.UserCenterCode;
import cn.bootx.usercenter.dto.role.RoleDto;
import cn.bootx.usercenter.dto.user.UserInfoDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = UserCenterCode.APPLICATION_NAME,contextId = "userRoleFeign",path = "/role")
public interface UserRoleFeign {

    @ApiOperation(value = "删除角色下所有用户绑定信息")
    @GetMapping(value = "/findRolesByUser")
    ResResult<List<RoleDto>> findRolesByUser(@RequestParam Long id) ;


    @ApiOperation(value = "根据用户ID获取到角色id集合")
    @GetMapping(value = "/findRoleIdsByUser")
    ResResult<List<Long>> findRoleIdsByUser(@RequestParam Long id);

    @ApiOperation(value = "根据角色ID获取到用户集合")
    @GetMapping(value = "/findUsersByRole")
    ResResult<List<UserInfoDto>> findUsersByRole(@RequestParam Long id);


    @ApiOperation(value = "根据角色ID获取到用户集合")
    @GetMapping(value = "/findUserIdsByRole")
    ResResult<List<Long>> findUserIdsByRole(@RequestParam Long id);
}
