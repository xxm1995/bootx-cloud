package cn.bootx.notice.client;


import cn.bootx.notice.dto.mail.SendMailParam;

/**
* 邮件发送
* @author xxm  
* @date 2020/5/3 14:18 
*/
public interface MailSendClient {

    /**
     * 发送html邮件 (直接http调用会取不到了租户id)
     */
    void sendHtmlMail(SendMailParam mailParam);

    /**
     * 简单邮件发送
     * @param email 邮件地址
     * @param subject 话题
     * @param msg 消息
     */
    void sentSimpleMail(String email,String subject,String msg);
}
