package cn.bootx.baseapi.client.feign;

import cn.bootx.baseapi.client.CaptchaClient;
import cn.bootx.baseapi.dto.captcha.CaptchaDataResult;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

/**   
* 
* @author xxm  
* @date 2021/4/11 
*/
@Component
@ConditionalOnMissingBean(CaptchaClient.class)
@RequiredArgsConstructor
public class CaptchaClientFeignImpl implements CaptchaClient {
    private final CaptchaFeign captchaFeign;
    @Override
    public CaptchaDataResult generateArithmetic(String key) {
        return captchaFeign.generateArithmeticByKey(key).getData();
    }

    @Override
    public CaptchaDataResult generateArithmetic() {
        return captchaFeign.generateArithmetic().getData();
    }

    @Override
    public boolean validation(String key, String value) {
        return captchaFeign.validation(key,value).getData();
    }
}
