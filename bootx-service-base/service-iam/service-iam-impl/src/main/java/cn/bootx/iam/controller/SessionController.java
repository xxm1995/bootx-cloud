package cn.bootx.iam.controller;

import cn.bootx.common.core.entity.UserDetail;
import cn.bootx.common.core.rest.Res;
import cn.bootx.common.core.rest.ResResult;
import cn.bootx.iam.core.auth.service.SessionService;
import cn.bootx.starter.auth.session.domain.LoginSession;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @author xxm
* @date 2021/8/18
*/
@Api(tags = "会话管理")
@RestController
@RequestMapping("/session")
@RequiredArgsConstructor
public class SessionController {
    private final SessionService sessionService;
    
    @ApiOperation("获取会话")
    @GetMapping("/get")
    public ResResult<LoginSession> get(){
        return Res.ok(sessionService.getSession());
    }

    @ApiOperation("获取登录用户")
    @GetMapping("/getUserDetail")
    public ResResult<UserDetail> getUserDetail(){
        return Res.ok(sessionService.getUserDetail());
    }
}
