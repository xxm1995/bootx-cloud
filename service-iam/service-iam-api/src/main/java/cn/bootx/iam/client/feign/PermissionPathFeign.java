package cn.bootx.iam.client.feign;

import cn.bootx.common.core.rest.ResResult;
import cn.bootx.iam.code.IamCode;
import cn.bootx.iam.dto.permission.PermissionPathDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = IamCode.APPLICATION_NAME,contextId = "permissionPathFeign",path = "/permission/path")
interface PermissionPathFeign {

    @ApiOperation("根据http方式和服务名进行查询")
    @GetMapping("/findByMethodAndService")
    ResResult<List<PermissionPathDto>> findByMethodAndService(@RequestParam String method, @RequestParam String serviceName);
}
