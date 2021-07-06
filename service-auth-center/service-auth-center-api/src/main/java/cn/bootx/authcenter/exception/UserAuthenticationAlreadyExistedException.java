package cn.bootx.authcenter.exception;


import cn.bootx.common.web.exception.BizException;

import java.io.Serializable;

import static cn.bootx.authcenter.code.AcErrorCodes.USER_AUTH_ALREADY_EXISTED;


/**
* 用户认证信息已存在异常
* @author xxm
* @date 2020/5/7 18:13
*/
public class UserAuthenticationAlreadyExistedException extends BizException implements Serializable {

    private static final long serialVersionUID = 6378800190493968995L;

    public UserAuthenticationAlreadyExistedException() {
        super(USER_AUTH_ALREADY_EXISTED, "用户认证已存在");
    }
}
