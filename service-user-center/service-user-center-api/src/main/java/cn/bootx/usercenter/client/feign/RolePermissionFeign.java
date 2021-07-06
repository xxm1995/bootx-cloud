package cn.bootx.usercenter.client.feign;

import cn.bootx.common.web.rest.ResResult;
import cn.bootx.usercenter.code.UserCenterCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = UserCenterCode.APPLICATION_NAME,contextId = "rolePermissionFeign",path = "/role")
interface RolePermissionFeign {

    @ApiOperation("获取角色授权")
    @GetMapping("/findPermissionIdsByRole")
    ResResult<List<Long>> findPermissionIdsByRole(@RequestParam Long roleId);

    @ApiOperation("获取角色授权(列表)")
    @GetMapping("/findPermissionIdsByRoles")
    ResResult<List<Long>> findPermissionIdsByRoles(@RequestParam List<Long> roleIds);

    @ApiOperation("根据用户id获取角色授权(列表)")
    @GetMapping("/findPermissionIdsByUser")
    ResResult<List<Long>> findPermissionIdsByUser(@RequestParam Long userId);
}
