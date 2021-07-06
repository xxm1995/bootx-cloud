package cn.bootx.authcenter.exception;


import cn.bootx.common.web.exception.BizException;

import java.io.Serializable;

import static cn.bootx.authcenter.code.AcErrorCodes.USER_AUTH_CODE_LOGIN_FAIL;

/**
* @author xxm
* @date 2020/5/7 18:01
*/
public class AuthCodeLoginException extends BizException implements Serializable {
    private static final long serialVersionUID = -3863511378105054043L;

    public AuthCodeLoginException() {
        super(USER_AUTH_CODE_LOGIN_FAIL, "登录失败");
    }
}
