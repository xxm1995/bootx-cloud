package cn.bootx.baseapi.client.feign;

import cn.bootx.baseapi.client.MessageCaptchaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

/**
 *
 * @author xxm
 * @date 2021/4/11
 */
@Component
@ConditionalOnMissingBean(MessageCaptchaClient.class)
@RequiredArgsConstructor
public class MessageCaptchaClientFeignImpl implements MessageCaptchaClient {
    private final MessageCaptchaFeign captchaFeign;
    @Override
    public String generateMessageCaptcha(String key) {
        return captchaFeign.generateMessageCaptcha(key).getData();
    }

    @Override
    public boolean validation(String key, String value) {
        return captchaFeign.validation(key,value).getData();
    }

    @Override
    public void remove(String key) {
        captchaFeign.remove(key);
    }
}
