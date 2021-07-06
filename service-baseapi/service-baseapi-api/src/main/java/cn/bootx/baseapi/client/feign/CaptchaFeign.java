package cn.bootx.baseapi.client.feign;

import cn.bootx.baseapi.code.BaseApiCode;
import cn.bootx.baseapi.dto.captcha.CaptchaDataResult;
import cn.bootx.common.web.rest.ResResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**   
* 验证码
* @author xxm  
* @date 2021/4/11 
*/
@FeignClient(name = BaseApiCode.APPLICATION_NAME,contextId = "captchaFeign",path = "/captcha")
public interface CaptchaFeign {

    @ApiOperation("生成算数验证码")
    @GetMapping("/generateByKey")
    ResResult<CaptchaDataResult> generateArithmeticByKey(@RequestParam String key);

    @ApiOperation("生成算数验证码")
    @GetMapping("/generate")
    ResResult<CaptchaDataResult> generateArithmetic();

    @ApiOperation("判断验证码是否正确")
    @GetMapping("/validationCaptcha")
    ResResult<Boolean> validation(@RequestParam String key, @RequestParam String value);
}
