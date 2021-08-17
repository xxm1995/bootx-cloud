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
    public CaptchaDataResult imgCaptcha() {
        return null;
    }

    @Override
    public boolean validateImgCaptcha(String key, String captcha) {
        return false;
    }

    @Override
    public void deleteImgCaptcha(String key) {

    }

    @Override
    public void sendSmsCaptcha(String phone) {

    }

    @Override
    public boolean validateSmsCaptcha(String phone, String captcha) {
        return false;
    }

    @Override
    public void deleteSmsCaptcha(String phone) {

    }
}
