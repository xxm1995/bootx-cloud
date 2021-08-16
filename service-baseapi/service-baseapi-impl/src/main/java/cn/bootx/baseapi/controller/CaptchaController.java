package cn.bootx.baseapi.controller;

import cn.bootx.baseapi.core.captcha.service.CaptchaService;
import cn.bootx.baseapi.dto.captcha.CaptchaDataResult;
import cn.bootx.common.core.rest.Res;
import cn.bootx.common.core.rest.ResResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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

    @ApiOperation("生成算数验证码")
    @GetMapping("/generateByKey")
    public ResResult<CaptchaDataResult> generateArithmetic(String key){
        return Res.ok(captchaService.generateArithmetic(key));
    }

    @ApiOperation("生成算数验证码")
    @GetMapping("/generate")
    public ResResult<CaptchaDataResult> generateArithmetic(){
        return Res.ok(captchaService.generateArithmetic());
    }

    @ApiOperation("判断验证码是否正确")
    @GetMapping("/validationCaptcha")
    public ResResult<Boolean> validation(String key,String value){
        return Res.ok(captchaService.validation(key,value));
    }

}
