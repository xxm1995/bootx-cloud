package cn.bootx.noticecenter.core.mail.dao;

import cn.bootx.noticecenter.dto.mail.MailConfigDto;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

/**   
* 邮件发件人缓存
* @author xxm  
* @date 2020/5/2 21:19
*/
@Service
public class MailSenderCache {

    public JavaMailSenderImpl getMailSender(MailConfigDto mailConfig) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailConfig.getHost());
        mailSender.setPort(mailConfig.getPort());
        mailSender.setUsername(mailConfig.getUsername());
        mailSender.setPassword(mailConfig.getPassword());

        Properties props = new Properties();
        // 判断是否是TLS
        if (Objects.equals(Optional.ofNullable(mailConfig.getSecurityType()).orElse(0), MailConfigDto.SECURITY_TYPE_TLS)) {
            props.setProperty("mail.smtp.starttls.enable", "true");
        } else if (Objects.equals(Optional.ofNullable(mailConfig.getSecurityType()).orElse(0), MailConfigDto.SECURITY_TYPE_SSL)) {
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.smtp.socketFactory.port", mailConfig.getPort() + "");
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
        }
        mailSender.setJavaMailProperties(props);

        return mailSender;
    }
}
