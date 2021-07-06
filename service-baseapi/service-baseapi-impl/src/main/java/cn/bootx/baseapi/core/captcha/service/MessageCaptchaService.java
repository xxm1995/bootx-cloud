package cn.bootx.baseapi.core.captcha.service;

import cn.bootx.baseapi.core.captcha.dao.MessageCaptchaRepository;
import cn.hutool.core.util.RandomUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
* 消息验证码
* @author xxm
* @date 2020/6/6 14:52
*/
@Slf4j
@Service
@AllArgsConstructor
public class MessageCaptchaService {
    private final MessageCaptchaRepository captchaRepository;

    /**
     * 生成消息验证码
     */
    public String generateMessageCaptcha(String key){
        String code = RandomUtil.randomNumbers(6);
        captchaRepository.saveToRedisWithExpireTime(key,code);
        return code;
    }

    /**
     * 判断验证码是否正确
     */
    public boolean validation(String key,String value){
        return captchaRepository.validation(key,value);
    }

    /**
     * 删除已被使用完验证码
     */
    public void remove(String key){
        captchaRepository.remove(key);
    }

}
