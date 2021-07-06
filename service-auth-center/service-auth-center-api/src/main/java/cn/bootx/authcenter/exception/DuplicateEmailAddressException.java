package cn.bootx.authcenter.exception;


import cn.bootx.common.web.exception.BizException;

import java.io.Serializable;

import static cn.bootx.authcenter.code.AcErrorCodes.DUPLICATE_EMAIL_ADDRESS;

/**
* @author xxm
* @date 2020/5/7 18:02
*/
public class DuplicateEmailAddressException extends BizException implements Serializable {
    private static final long serialVersionUID = -4870773676058365148L;

    public DuplicateEmailAddressException() {
        super(DUPLICATE_EMAIL_ADDRESS, "电子邮件地址重复");
    }
}
