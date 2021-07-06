package cn.bootx.authcenter.exception;


import cn.bootx.common.web.exception.BizException;

import java.io.Serializable;

import static cn.bootx.authcenter.code.AcErrorCodes.UN_SUPPORTED_LOGIN;

/**   
* 不支持的登录方式
* @author xxm  
* @date 2020/5/7 18:26
*/
public class UnSupportedLoginException extends BizException implements Serializable {
    private static final long serialVersionUID = -8347955323710649299L;

    public UnSupportedLoginException() {
        super(UN_SUPPORTED_LOGIN, "不支持的登录方式");
    }
}
