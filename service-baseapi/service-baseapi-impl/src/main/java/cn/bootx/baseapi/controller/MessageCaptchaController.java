package cn.bootx.baseapi.controller;

import cn.bootx.baseapi.core.captcha.service.MessageCaptchaService;
import cn.bootx.common.core.rest.Res;
import cn.bootx.common.core.rest.ResResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "消息验证码")
@RestController
@RequestMapping("/captcha/message")
@AllArgsConstructor
public class MessageCaptchaController {
    private final MessageCaptchaService messageCaptchaService;

    @ApiOperation("生成消息验证码")
    @PostMapping("/generateMessageCaptcha")
    public ResResult<String> generateMessageCaptcha(String key){
        return Res.ok(messageCaptchaService.generateMessageCaptcha(key));
    }

    @ApiOperation("判断验证码是否正确")
    @PostMapping("/validation")
    public ResResult<Boolean> validation(String key,String value){
        return Res.ok(messageCaptchaService.validation(key,value));
    }

    @ApiOperation("删除")
    @DeleteMapping("/remove")
    public ResResult<Void> remove(String key){
        messageCaptchaService.remove(key);
        return Res.ok();
    }
}
