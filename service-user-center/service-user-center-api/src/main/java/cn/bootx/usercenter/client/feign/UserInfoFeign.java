package cn.bootx.usercenter.client.feign;

import cn.bootx.common.web.rest.ResResult;
import cn.bootx.usercenter.code.UserCenterCode;
import cn.bootx.usercenter.dto.user.UserInfoDto;
import cn.bootx.usercenter.param.user.UserInfoParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = UserCenterCode.APPLICATION_NAME,contextId = "userInfoFeign",path = "/user")
public interface UserInfoFeign {

    @ApiOperation(value = "根据用户id 获取 UserInfo")
    @GetMapping("/getById")
    ResResult<UserInfoDto> getById(@RequestParam Long id);

    @ApiOperation(value = "根据邮箱查询用户")
    @GetMapping("/getByEmail")
    ResResult<UserInfoDto> getByEmail(@RequestParam String email);

    @ApiOperation(value = "根据手机号查询用户")
    @GetMapping("/getByPhone")
    ResResult<UserInfoDto> getByPhone(@RequestParam String phone);

    @ApiOperation("添加用户")
    @PostMapping("/addUserInfo")
    ResResult<UserInfoDto> addUserInfo(@RequestBody UserInfoParam userInfoParam);
}

