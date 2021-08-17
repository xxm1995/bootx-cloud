package cn.bootx.baseapi.client;

import cn.bootx.baseapi.dto.captcha.CaptchaDataResult;

/**
* 验证码
* @author xxm  
* @date 2020/5/8 21:55 
*/
public interface CaptchaClient {

    /**
     * 获取图片验证码
     */
    CaptchaDataResult imgCaptcha();

    /**
     * 校验图片验证码
     */
    boolean validateImgCaptcha(String key, String captcha);

    /**
     * 失效图片验证码
     */
    void deleteImgCaptcha(String key);
    
    /**
     * 发送手机验证码
     */
    void sendSmsCaptcha(String phone);

    /**
     * 校验手机验证码
     */
    boolean validateSmsCaptcha(String phone, String captcha);

    /**
     * 失效手机验证码
     */
    void deleteSmsCaptcha(String phone);
}
