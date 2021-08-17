package cn.bootx.iam.client.feign;

import cn.bootx.common.core.rest.ResResult;
import cn.bootx.iam.code.IamCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = IamCode.APPLICATION_NAME,contextId = "rolePathFeign",path = "/role/path")
interface RolePathFeign {

    @ApiOperation("根据用户id获取请求权限id(列表)")
    @GetMapping("/findPathIdsByUser")
    ResResult<List<Long>> findPathIdsByUser(@RequestParam Long userId);
}
