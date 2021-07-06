package cn.bootx.baseapi.client;

/**
* 消息验证码
* @author xxm
* @date 2020/6/6 15:24
*/
public interface MessageCaptchaClient {

    /**
     * 生成消息验证码
     */
    String generateMessageCaptcha(String key);

    /**
     * 判断验证码是否正确
     */
    boolean validation(String key, String value);

    /**
     * 删除已被使用完验证码
     */
    void remove(String key);
}
