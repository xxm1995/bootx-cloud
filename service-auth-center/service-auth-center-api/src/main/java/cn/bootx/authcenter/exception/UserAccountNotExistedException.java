package cn.bootx.authcenter.exception;


import cn.bootx.common.web.exception.BizException;

import java.io.Serializable;

import static cn.bootx.authcenter.code.AcErrorCodes.USER_ACCOUNT_NOT_EXISTED;

/**   
* 用户账号不存在异常
* @author xxm  
* @date 2020/5/7 18:12 
*/
public class UserAccountNotExistedException extends BizException implements Serializable {
    private static final long serialVersionUID = -9159937753859585550L;

    public UserAccountNotExistedException() {
        super(USER_ACCOUNT_NOT_EXISTED, "用户帐号不存在");
    }
}
