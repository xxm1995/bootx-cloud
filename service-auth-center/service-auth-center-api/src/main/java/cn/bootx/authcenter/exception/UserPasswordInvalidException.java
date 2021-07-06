package cn.bootx.authcenter.exception;


import cn.bootx.common.web.exception.BizException;

import java.io.Serializable;

import static cn.bootx.authcenter.code.AcErrorCodes.USER_PASSWORD_INVALID;

/**
* 用户密码不正确异常
* @author xxm
* @date 2020/5/7 18:16
*/
public class UserPasswordInvalidException extends BizException implements Serializable {
    private static final long serialVersionUID = 6321083408077778554L;

    public UserPasswordInvalidException() {
        super(USER_PASSWORD_INVALID, "用户密码不正确");
    }
}
