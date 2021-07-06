package cn.bootx.authcenter.exception;


import cn.bootx.common.web.exception.BizException;

import java.io.Serializable;

import static cn.bootx.authcenter.code.AcErrorCodes.USER_ACCOUNT_UNACTIVE;

/**
* @author xxm
* @date 2020/5/7 18:01
*/
public class AccountUnActiveException extends BizException implements Serializable {
    private static final long serialVersionUID = 5856039760588541750L;

    public AccountUnActiveException() {
        super(USER_ACCOUNT_UNACTIVE, "帐户无效");
    }
}
