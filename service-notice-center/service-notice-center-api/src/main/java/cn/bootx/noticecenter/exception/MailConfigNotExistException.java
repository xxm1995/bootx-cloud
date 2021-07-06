package cn.bootx.noticecenter.exception;


import cn.bootx.common.web.exception.FatalException;

import static cn.bootx.noticecenter.code.ErrorCodes.MAIL_CONFIG_NOT_EXIST;


/**
 * 邮箱配置不存在异常
 */
public class MailConfigNotExistException extends FatalException {

    public MailConfigNotExistException() {
        super(MAIL_CONFIG_NOT_EXIST, "邮箱配置不存在异常");
    }
}
