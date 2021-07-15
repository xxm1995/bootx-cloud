package cn.bootx.iam.controller;

import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.common.web.util.ValidationUtil;
import cn.bootx.iam.core.login.service.LoginService;
import cn.bootx.iam.dto.auth.AuthInfoResult;
import cn.bootx.iam.param.login.LoginPasswordParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
* @author xxm
* @date 2021/7/13
*/
@Api(tags = "登录")
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @ApiOperation("密码登陆")
    @PostMapping("/password")
    public ResResult<AuthInfoResult> loginPassword(@RequestBody LoginPasswordParam loginParam){
        ValidationUtil.validateParam(loginParam);
        return Res.ok(loginService.loginPassword(loginParam));
    }

    @ApiOperation("获取登录信息")
    @GetMapping("/getUserInfo")
    public ResResult<AuthInfoResult> getUserInfo(){
        return Res.ok(loginService.getUserInfo());
    }
    
    @ApiOperation("退出")
    @PostMapping("/logout")
    public ResResult<Void> logout(){
        loginService.logout();
        return Res.ok();
    }
}
