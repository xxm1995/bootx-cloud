package cn.bootx.iam.controller;

import cn.bootx.common.core.rest.Res;
import cn.bootx.common.core.rest.ResResult;
import cn.bootx.iam.core.login.service.LoginUserService;
import cn.bootx.iam.dto.auth.AuthInfoResult;
import cn.bootx.iam.dto.login.MenuAndPermissionDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
*
* @author xxm
* @date 2021/7/12
*/
@Api(tags = "登录用户接口")
@RestController
@RequestMapping("/login/user")
@RequiredArgsConstructor
public class LoginUserController {
    private final LoginUserService loginUserService;

    @ApiOperation("查询用户路由菜单和按钮权限")
    @GetMapping("/getUserPermission")
    public ResResult<MenuAndPermissionDto> getUserPermission(){
        return Res.ok(loginUserService.getUserPermission());
    }

    @ApiOperation("获取登录信息")
    @GetMapping("/getUserInfo")
    public ResResult<AuthInfoResult> getUserInfo(){
        return Res.ok(loginUserService.getUserInfo());
    }
}
