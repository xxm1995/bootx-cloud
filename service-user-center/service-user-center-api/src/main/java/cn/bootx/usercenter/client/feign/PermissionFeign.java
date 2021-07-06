package cn.bootx.usercenter.client.feign;

import cn.bootx.common.web.rest.ResResult;
import cn.bootx.usercenter.code.UserCenterCode;
import cn.bootx.usercenter.dto.permission.PermissionDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = UserCenterCode.APPLICATION_NAME,contextId = "permissionFeign",path = "/permission")
interface PermissionFeign {


    @ApiOperation("根据http方式和服务名进行查询")
    @GetMapping("/findByMethodAndService")
    ResResult<List<PermissionDto>> findByMethodAndService(@RequestParam String method, @RequestParam String serviceName);
}
