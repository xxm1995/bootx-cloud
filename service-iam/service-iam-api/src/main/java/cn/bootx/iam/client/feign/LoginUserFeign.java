package cn.bootx.iam.client.feign;

import cn.bootx.common.web.rest.ResResult;
import cn.bootx.iam.code.IamCode;
import cn.bootx.iam.dto.auth.AuthInfoResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = IamCode.APPLICATION_NAME,contextId = "loginUserFeign",path = "/login/user")
public interface LoginUserFeign {

    @ApiOperation("获取登录信息")
    @GetMapping("/getUserInfo")
    ResResult<AuthInfoResult> getUserInfo();
}
