package cn.bootx.authcenter.controller;

import cn.bootx.authcenter.code.AcErrorCodes;
import cn.bootx.authcenter.core.auth.service.UserAuthService;
import cn.bootx.authcenter.dto.PasswordModificationParam;
import cn.bootx.authcenter.dto.UserAuthDto;
import cn.bootx.authcenter.param.UserAuthParam;
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
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
* @author xxm
* @date 2020/4/25 19:56
*/
@Api(tags = "认证账户管理")
@RestController
@RequiredArgsConstructor
public class UserAuthController {

    private final UserAuthService userAuthService;

    @ApiOperation("新增用户账号和用户信息")
    @ApiResponses(value = {
            @ApiResponse(code = CommonErrorCodes.VALIDATE_PARAMETERS_ERROR, message = "参数验证未通过"),
            @ApiResponse(code = UcErrorCodes.USER_INFO_NOT_EXISTS, message = "用户信息不存在"),
            @ApiResponse(code = UcErrorCodes.NONE_PHONE_AND_EMAIL, message = "手机号和邮箱均不存在"),
            @ApiResponse(code = AcErrorCodes.USER_AUTH_ALREADY_EXISTED, message = "用户认证信息已存在"),
            @ApiResponse(code = AcErrorCodes.USER_ACCOUNT_ALREADY_EXISTED, message = "用户账号已存在"),
    })
    @PostMapping("/addAuthAndInfo")
    public ResResult<UserAuthDto> addUserAuthAndInfo(@RequestBody UserAuthParam userAuthParam) {
        ValidationUtil.validateParam(userAuthParam);
        return Res.ok(userAuthService.saveUserAuthAndInfo(userAuthParam));
    }


    @ApiOperation(value = "修改账号登录密码")
    @ApiResponses(value = {
            @ApiResponse(code = CommonErrorCodes.VALIDATE_PARAMETERS_ERROR, message = "参数验证未通过"),
            @ApiResponse(code = AcErrorCodes.USER_OLD_PASSWORD_INVALID, message = "原密码错误"),
            @ApiResponse(code = AcErrorCodes.USER_AUTH_NOT_EXISTED, message = "用户账号不存在"),
    })
    @PostMapping(value = "/password/modify", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<String> modifyPassword(@RequestBody PasswordModificationParam param) {
        ValidationUtil.validateParam(param);

        String passwordOld = param.getPasswordOld();
        String passwordNew = param.getPasswordNew();
        userAuthService.changePassword( param.getUid(), passwordOld, passwordNew);
        return Res.ok();
    }

    @ApiOperation(value = "激活账号")
    @ApiResponses(value = {
            @ApiResponse(code = CommonErrorCodes.VALIDATE_PARAMETERS_ERROR, message = "参数验证未通过"),
            @ApiResponse(code = AcErrorCodes.USER_AUTH_NOT_EXISTED, message = "用户认证信息不存在")
    })
    @PostMapping("/acitivation")
    public ResResult<UserAuthDto> activeAccountById(Long id) {
        UserAuthDto dto = userAuthService.activeAccountById(id);
        return Res.ok(dto);
    }
}
