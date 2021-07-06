package cn.bootx.authcenter.exception;


import cn.bootx.common.web.exception.BizException;

import java.io.Serializable;

import static cn.bootx.authcenter.code.AcErrorCodes.DUPLICATE_PHONE_NUMBER;

/**   
* 用户手机重复异常
* @author xxm  
* @date 2020/5/7 18:02 
*/
public class DuplicatePhoneNumberException extends BizException implements Serializable {
    private static final long serialVersionUID = 7941218998417741410L;

    public DuplicatePhoneNumberException() {
        super(DUPLICATE_PHONE_NUMBER, "手机号重复");
    }
}
