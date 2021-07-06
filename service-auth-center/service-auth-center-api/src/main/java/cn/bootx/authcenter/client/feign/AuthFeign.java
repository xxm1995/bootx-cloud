package cn.bootx.authcenter.client.feign;

import cn.bootx.authcenter.code.AuthCenterCode;
import cn.bootx.authcenter.dto.*;
import cn.bootx.common.web.rest.ResResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = AuthCenterCode.APPLICATION_NAME,contextId = "authFeign",path = "/auth")
public interface AuthFeign {

    @ApiOperation("用户账号/密码登录")
    @PostMapping("/login")
    ResResult<UserAuthResult> login(@RequestBody LoginParam param);

    @ApiOperation("手机号与验证码登录(此接口需要依赖上层应用做验证码校验)")
    @PostMapping("/loginByPhone")
    ResResult<UserAuthResult> loginByPhone(@RequestBody PhoneLoginParam param);

    @ApiOperation("邮箱与验证码登录(此接口需要依赖上层应用做验证码校验)")
    @PostMapping("/loginByEmail")
    ResResult<UserAuthResult> loginByEmail(@RequestBody MailLoginParam param);

    @ApiOperation("authCode登录")
    @PostMapping("/loginByAuthCode")
    ResResult<UserAuthResult> loginByAuthCode(@RequestBody AuthCodeLoginParam param);

    @ApiOperation("通过 token 获取登录用户的基本信息")
    @GetMapping("/getUserInfoByToken")
    ResResult<UserAuthResult> getUserInfoByToken(@RequestParam String token) ;


    @ApiOperation("用户登出")
    @PostMapping("/logoutByToken")
    ResResult<Boolean> logoutByToken(@RequestParam String token);
}
