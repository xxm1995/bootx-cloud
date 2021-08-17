package cn.bootx.baseapi.controller;

import cn.bootx.baseapi.core.captcha.service.CaptchaService;
import cn.bootx.baseapi.dto.captcha.CaptchaDataResult;
import cn.bootx.common.core.rest.Res;
import cn.bootx.common.core.rest.ResResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @author xxm
* @date 2020/5/8 21:12
*/
@Api(tags = "验证码服务")
@RestController
@RequestMapping("/captcha")
@AllArgsConstructor
public class CaptchaController {
    private final CaptchaService captchaService;

    @ApiOperation("获取图片验证码")
    @PostMapping("/imgCaptcha")
    public ResResult<CaptchaDataResult> imgCaptcha(){
        return Res.ok(captchaService.imgCaptcha());
    }

    @ApiOperation("校验图片验证码")
    @PostMapping("/validateImgCaptcha")
    public ResResult<Boolean> validateImgCaptcha(String key, String captcha){
        return Res.ok(captchaService.validateImgCaptcha(key,captcha));
    }

    @ApiOperation("失效图片验证码")
    @DeleteMapping("/deleteImgCaptcha")
    public ResResult<Void> deleteImgCaptcha(String key){
        captchaService.deleteImgCaptcha(key);
        return Res.ok();
    }

    @ApiOperation("发送短信验证码")
    @PostMapping("/sendSmsCaptcha")
    public ResResult<Void> sendSmsCaptcha(String phone){
        captchaService.sendSmsCaptcha(phone);
        return Res.ok();
    }


    @ApiOperation("校验短信验证码")
    @PostMapping("/validateSmsCaptcha")
    public ResResult<Boolean> validateSmsCaptcha(String phone, String captcha){
        return Res.ok(captchaService.validateSmsCaptcha(phone,captcha));
    }

    @ApiOperation("失效短信验证码")
    @DeleteMapping("/deleteSmsCaptcha")
    public ResResult<Void> deleteSmsCaptcha(String phone){
        captchaService.deleteSmsCaptcha(phone);
        return Res.ok();
    }

}
