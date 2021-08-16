package cn.bootx.noticecenter.exception;

import cn.bootx.common.core.exception.FatalException;

import static cn.bootx.noticecenter.code.ErrorCodes.MAIL_TEMPLATE_NOT_EXIST;

/**
* 模板不存在
* @author xxm
* @date 2020/11/18
*/
public class MailTemplateNotExistException extends FatalException {
    public MailTemplateNotExistException() {
        super(MAIL_TEMPLATE_NOT_EXIST, "邮箱模板不存在异常");
    }

}
