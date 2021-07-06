package cn.bootx.usercenter.controller;

import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.usercenter.core.user.service.UserInfoService;
import cn.bootx.usercenter.dto.user.UserInfoDto;
import cn.bootx.usercenter.param.user.UserInfoParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
* @author xxm
* @date 2020/4/25 20:02
*/
@Api(tags = "用户")
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserInfoController {
	private final UserInfoService userInfoService;

    @ApiOperation(value = "根据用户id查询用户")
    @GetMapping("/getById")
    public ResResult<UserInfoDto> getById(Long id) {
        return Res.ok(userInfoService.getById(id));
    }

    @ApiOperation(value = "根据邮箱查询用户")
    @GetMapping("/getByEmail")
    public ResResult<UserInfoDto> getByEmail(String email) {
        return Res.ok(userInfoService.getByEmail(email));
    }

    @ApiOperation(value = "根据手机号查询用户")
    @GetMapping("/getByPhone")
    public ResResult<UserInfoDto> getByPhone(String phone) {
        return Res.ok(userInfoService.getByPhone(phone));
    }

    @ApiOperation("添加用户")
    @PostMapping("/addUserInfo")
    public ResResult<UserInfoDto> addUserInfo(@RequestBody UserInfoParam userInfoParam){
        return Res.ok(userInfoService.addUserInfo(userInfoParam));
    }

}
