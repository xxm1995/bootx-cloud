package cn.bootx.engine.shop.exception;


import cn.bootx.common.web.exception.BizException;

public class InnerServiceException extends BizException {

    public InnerServiceException(int code, String message) {
        super(code, message);
    }
}