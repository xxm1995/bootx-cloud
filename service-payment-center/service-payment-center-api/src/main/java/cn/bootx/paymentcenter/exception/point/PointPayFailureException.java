package cn.bootx.paymentcenter.exception.point;

import cn.bootx.common.core.exception.BizException;

/**
* @author xxm
* @date 2020/12/12
*/
public class PointPayFailureException extends BizException {

    public PointPayFailureException(int code, String message) {
        super(code, message);
    }

}