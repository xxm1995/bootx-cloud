package cn.bootx.authcenter.controller;

import cn.bootx.authcenter.code.AcErrorCodes;
import cn.bootx.authcenter.core.auth.service.AuthService;
import cn.bootx.authcenter.dto.*;
import cn.bootx.common.web.rest.CommonErrorCodes;
import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.common.web.util.ValidationUtil;
import cn.bootx.usercenter.code.UcErrorCodes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**   
* @author xxm
* @date 2021/6/2 
*/
@Api(tags = "认证")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @ApiOperation("用户账号/密码登录")
    @ApiResponses(value = {
            @ApiResponse(code = CommonErrorCodes.VALIDATE_PARAMETERS_ERROR, message = "参数验证未通过"),
            @ApiResponse(code = UcErrorCodes.USER_INFO_NOT_EXISTS, message = "用户信息不存在"),
            @ApiResponse(code = AcErrorCodes.USER_ACCOUNT_NOT_EXISTED, message = "用户账号不存在"),
            @ApiResponse(code = AcErrorCodes.USER_AUTH_NOT_EXISTED, message = "认证信息不存在"),
            @ApiResponse(code = AcErrorCodes.USER_ACCOUNT_UNACTIVE, message = "用户已被禁用"),
            @ApiResponse(code = AcErrorCodes.USER_PASSWORD_INVALID, message = "用户密码错误"),
    })
    @PostMapping("/login")
    public ResResult<UserAuthResult> login(@RequestBody LoginParam param) {
        ValidationUtil.validateParam(param);
        return Res.ok(authService.loginByAllPattern(param));
    }

    @ApiOperation("手机号与验证码登录(此接口需要依赖上层应用做验证码校验)")
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "登录成功"),
            @ApiResponse(code = CommonErrorCodes.VALIDATE_PARAMETERS_ERROR, message = "参数验证未通过"),
            @ApiResponse(code = AcErrorCodes.USER_ACCOUNT_NOT_EXISTED, message = "账号不存在"),
            @ApiResponse(code = AcErrorCodes.USER_ACCOUNT_UNACTIVE, message = "账号已被禁用"),
    })
    @PostMapping("/loginByPhone")
    public ResResult<UserAuthResult> loginByPhone(@RequestBody PhoneLoginParam param) {
        ValidationUtil.validateParam(param);
        return Res.ok(authService.loginByPhone(param));
    }

    @ApiOperation("邮箱与验证码登录(此接口需要依赖上层应用做验证码校验)")
    @ApiResponses(value = {
            @ApiResponse(code = CommonErrorCodes.VALIDATE_PARAMETERS_ERROR, message = "参数验证未通过"),
            @ApiResponse(code = AcErrorCodes.USER_ACCOUNT_NOT_EXISTED, message = "账号不存在"),
            @ApiResponse(code = AcErrorCodes.USER_ACCOUNT_UNACTIVE, message = "账号已被禁用"),
    })
    @PostMapping("/loginByEmail")
    public ResResult<UserAuthResult> loginByEmail(@RequestBody MailLoginParam param) {
        ValidationUtil.validateParam(param);
        return Res.ok(authService.loginByEmail(param));
    }

    @ApiOperation("authCode登录")
    @ApiResponses(value = {
            @ApiResponse(code = CommonErrorCodes.VALIDATE_PARAMETERS_ERROR, message = "参数验证未通过"),
            @ApiResponse(code = AcErrorCodes.USER_AUTH_CODE_LOGIN_FAIL, message = "用户自动登录失败，AuthCode不存在"),
            @ApiResponse(code = AcErrorCodes.USER_ACCOUNT_NOT_EXISTED, message = "账号不存在"),
            @ApiResponse(code = AcErrorCodes.USER_ACCOUNT_UNACTIVE, message = "账号已被禁用"),
    })
    @PostMapping("/loginByAuthCode")
    public ResResult<UserAuthResult> loginByAuthCode(@RequestBody AuthCodeLoginParam param) {
        ValidationUtil.validateParam(param);
        return Res.ok(authService.loginByAuthCode(param));
    }

    @ApiOperation("通过 token 获取登录用户的基本信息")
    @GetMapping("/getUserInfoByToken")
    public ResResult<UserAuthResult> getUserInfoByToken(String token) {
        return Res.ok(authService.getUserInfoByToken(token));
    }

    @ApiOperation("用户登出")
    @ApiResponses(value = {
            @ApiResponse(code = CommonErrorCodes.VALIDATE_PARAMETERS_ERROR, message = "参数验证未通过")
    })
    @PostMapping("/logoutByToken")
    public ResResult<Boolean> logoutByToken(String token) {
        authService.logoutByToken(token);
        return Res.ok(true);
    }

}
