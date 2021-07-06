package cn.bootx.noticecenter.client;

/**   
* 发送消息验证码服务
* @author xxm  
* @date 2020/6/6 16:36 
*/
public interface CaptchaMessageClient {

    /**
     * 发送验证码/邮件
     */
    String sendCaptchaByEmail(String email);

    /**
     * 发送验证码/短信
     */
    String sendCaptchaBySms(String phone);
}
