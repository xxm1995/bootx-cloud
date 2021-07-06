package cn.bootx.noticecenter.controller;

import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.noticecenter.core.mail.service.sent.MailSendService;
import cn.bootx.noticecenter.dto.mail.MailMailParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @author xxm
* @date 2021/7/6
*/
@Api(tags = "邮件发送")
@RestController
@RequestMapping("/mail/send")
@RequiredArgsConstructor
public class MailSendController {
    private final MailSendService mailSendService;

    @ApiOperation(value = "发送邮件")
    @PostMapping(value = "/sendMail")
    public ResResult<Boolean> sendMail(MailMailParam mailParam){
        mailSendService.sendHtmlMail(mailParam);
        return Res.ok(true);
    }

    @ApiOperation(value = "发送简单邮件")
    @PostMapping(value = "/sentSimpleMail")
    public ResResult<Boolean> sentSimpleMail(String email,String subject,String msg){
        mailSendService.sentSimpleMail(email,subject,msg);
        return Res.ok(true);
    }
}
