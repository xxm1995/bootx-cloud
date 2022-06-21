package cn.bootx.iam.controller;

import cn.bootx.common.web.rest.PageResult;
import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.common.web.rest.param.PageParam;
import cn.bootx.iam.core.user.service.UserInfoService;
import cn.bootx.iam.core.user.service.UserQueryService;
import cn.bootx.iam.dto.user.UserInfoDto;
import cn.bootx.iam.param.user.UserInfoParam;
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
	private final UserQueryService userQueryService;

    @ApiOperation(value = "根据用户id查询用户")
    @GetMapping("/getById")
    public ResResult<UserInfoDto> getById(Long id) {
        return Res.ok(userInfoService.findById(id));
    }

    @ApiOperation(value = "根据邮箱查询用户")
    @GetMapping("/getByEmail")
    public ResResult<UserInfoDto> getByEmail(String email) {
        return Res.ok(userInfoService.findByEmail(email));
    }

    @ApiOperation(value = "根据手机号查询用户")
    @GetMapping("/getByPhone")
    public ResResult<UserInfoDto> getByPhone(String phone) {
        return Res.ok(userInfoService.findByPhone(phone));
    }

    @ApiOperation("添加用户")
    @PostMapping("/addUserInfo")
    public ResResult<UserInfoDto> addUserInfo(@RequestBody UserInfoParam userInfoParam){
        return Res.ok(userInfoService.addUserInfo(userInfoParam));
    }

    @ApiOperation("分页")
    @GetMapping("/page")
    public ResResult<PageResult<UserInfoDto>> page(PageParam pageParam){
        return Res.ok(userQueryService.page(pageParam));
    }

}