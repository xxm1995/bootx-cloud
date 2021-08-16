package cn.bootx.baseapi.client.feign;

import cn.bootx.baseapi.code.BaseApiCode;
import cn.bootx.common.core.rest.ResResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
* 消息验证码
* @author xxm  
* @date 2021/4/11 
*/
@FeignClient(name = BaseApiCode.APPLICATION_NAME,contextId = "messageCaptchaFeign",path = "/captcha/message")
public interface MessageCaptchaFeign {

    @ApiOperation("生成消息验证码")
    @PostMapping("/generateMessageCaptcha")
    ResResult<String> generateMessageCaptcha(@RequestParam String key);

    @ApiOperation("判断验证码是否正确")
    @PostMapping("/validation")
    ResResult<Boolean> validation(@RequestParam String key,@RequestParam String value);

    @ApiOperation("删除")
    @DeleteMapping("/remove")
    ResResult<Void> remove(@RequestParam String key);
}
