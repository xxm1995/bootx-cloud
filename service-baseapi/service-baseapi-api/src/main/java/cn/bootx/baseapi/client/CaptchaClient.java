package cn.bootx.baseapi.client;

import cn.bootx.baseapi.dto.captcha.CaptchaDataResult;

/**
* 验证码
* @author xxm  
* @date 2020/5/8 21:55 
*/
public interface CaptchaClient {

    /**
     * 生成算数二维码
     */
    CaptchaDataResult generateArithmetic(String key);

    /**
     * 生成算数二维码
     */
    CaptchaDataResult generateArithmetic();

    /**
     * 验证算数二维码
     */
    boolean validation(String key,String value);
}
