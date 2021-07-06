package cn.bootx.authcenter.exception;


import cn.bootx.common.web.exception.BizException;

import java.io.Serializable;

import static cn.bootx.authcenter.code.AcErrorCodes.USER_ACCOUNT_ALREADY_EXISTED;


/**   
* 用户账号已存在异常
* @author xxm  
* @date 2020/5/7 18:12 
*/
public class UserAccountAlreadyExistedException extends BizException implements Serializable {
    private static final long serialVersionUID = -6117648507163599392L;

    public UserAccountAlreadyExistedException() {
        super(USER_ACCOUNT_ALREADY_EXISTED, "用户帐户已存在");
    }
}
