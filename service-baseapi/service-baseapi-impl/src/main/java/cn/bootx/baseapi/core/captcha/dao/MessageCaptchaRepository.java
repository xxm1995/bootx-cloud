package cn.bootx.baseapi.core.captcha.dao;

import cn.bootx.starter.redis.RedisClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**
* 消息验证码
* @author xxm
* @date 2020/6/6 14:54
*/
@Repository
@AllArgsConstructor
public class MessageCaptchaRepository {
    private static final String ARITHMETIC_CAPTCHA = "captcha:message:";
    private static final long KEY_TIMEOUT = 30*1000*60L;

    private final RedisClient redisClient;

    /**
     * 存储到 缓存
     */
    public void saveToRedisWithExpireTime(String key, String value) {
        redisClient.setWithTimeout(ARITHMETIC_CAPTCHA + key, value, KEY_TIMEOUT);
    }

    /**
     * 判断验证码是否正确 并删除
     */
    public boolean validation(String key,String value){
        return redisClient.exists(ARITHMETIC_CAPTCHA + key);
    }

    /**
     * 判断验证码是否正确 并删除
     */
    public void remove(String key){
            redisClient.deleteKey(ARITHMETIC_CAPTCHA + key);
    }

}
