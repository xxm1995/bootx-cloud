package cn.bootx.authcenter.exception;


import cn.bootx.common.web.exception.FatalException;

import java.io.Serializable;

import static cn.bootx.authcenter.code.AcErrorCodes.USER_AUTH_NOT_EXISTED;


/**
* 用户认证信息没找到异常
* @author xxm  
* @date 2020/5/7 18:27
*/
public class UserAuthenticationNotExistedException extends FatalException implements Serializable {

    private static final long serialVersionUID = 8299638106174339992L;

    public UserAuthenticationNotExistedException() {
        super(USER_AUTH_NOT_EXISTED, "用户验证不存在");
    }
}
