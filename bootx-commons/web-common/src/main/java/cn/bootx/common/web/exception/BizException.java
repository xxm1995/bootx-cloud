package cn.bootx.common.web.exception;

import cn.bootx.common.web.code.CommonCode;

import java.io.Serializable;

/**
 * 业务异常基类
 */
public class BizException extends ErrorCodeRuntimeException implements Serializable {

    private static final long serialVersionUID = -3958262511648424790L;

    public BizException(int code, String message) {
        super(code, message);
    }


    public BizException(String message) {
        super(CommonCode.FAIL_CODE, message);
    }

    public BizException(){
        super();
    }

}
