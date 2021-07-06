package cn.bootx.authcenter.client.feign;

import cn.bootx.authcenter.code.AuthCenterCode;
import cn.bootx.authcenter.dto.PasswordModificationParam;
import cn.bootx.authcenter.dto.UserAuthDto;
import cn.bootx.authcenter.param.UserAuthParam;
import cn.bootx.common.web.rest.ResResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = AuthCenterCode.APPLICATION_NAME,contextId = "userAuthFeign",path = "/auth")
public interface UserAuthFeign {

    @ApiOperation("新增用户账号和用户信息")
    @PostMapping("/addAuthAndInfo")
    ResResult<UserAuthDto> addUserAuthAndInfo(@RequestBody UserAuthParam userAuthParam);


    @ApiOperation(value = "修改账号登录密码")
    @PostMapping(value = "/password/modify", produces = MediaType.APPLICATION_JSON_VALUE)
    ResResult<String> modifyPassword(@RequestBody PasswordModificationParam param);

    @ApiOperation(value = "激活账号")
    @PostMapping("/acitivation")
    ResResult<UserAuthDto> activeAccountById(Long id);
}
