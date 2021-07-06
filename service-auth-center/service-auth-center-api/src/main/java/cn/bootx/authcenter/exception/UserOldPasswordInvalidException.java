package cn.bootx.authcenter.exception;


import cn.bootx.common.web.exception.BizException;

import java.io.Serializable;

import static cn.bootx.authcenter.code.AcErrorCodes.USER_OLD_PASSWORD_INVALID;

/**
* 用户原密码不正确异常
* @author xxm
* @date 2020/5/7 18:15
*/
public class UserOldPasswordInvalidException extends BizException implements Serializable {
    private static final long serialVersionUID = -58782621855860179L;

    public UserOldPasswordInvalidException() {
        super(USER_OLD_PASSWORD_INVALID, "用户原密码错误");
    }
}
