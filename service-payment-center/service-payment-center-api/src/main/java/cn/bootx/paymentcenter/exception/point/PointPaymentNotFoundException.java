package cn.bootx.paymentcenter.exception.point;

import cn.bootx.common.web.exception.BizException;

import static cn.bootx.paymentcenter.code.PaymentCenterErrorCode.POINT_PAYMENT_NOT_FOUND;

public class PointPaymentNotFoundException extends BizException {
    public PointPaymentNotFoundException() {
        super(POINT_PAYMENT_NOT_FOUND, "找不到积分付款记录.");
    }
}
