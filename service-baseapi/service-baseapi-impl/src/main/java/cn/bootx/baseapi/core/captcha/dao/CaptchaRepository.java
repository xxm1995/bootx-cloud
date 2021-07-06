package cn.bootx.baseapi.core.captcha.dao;

import cn.bootx.starter.redis.RedisClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

/**   
* 验证码相关存储
* @author xxm  
* @date 2020/5/24 15:47 
*/
@Repository
@AllArgsConstructor
public class CaptchaRepository {
    private static final String ARITHMETIC_CAPTCHA = "captcha:arithmetic:";
    private static final long KEY_TIMEOUT = 5*1000*60L;

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
    public boolean validationAndRemove(String key,String value){
        boolean equals = redisClient.exists(ARITHMETIC_CAPTCHA + key);
        if (equals){
            redisClient.deleteKey(ARITHMETIC_CAPTCHA + key);
        }
        return equals;
    }
}
