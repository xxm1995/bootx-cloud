package cn.bootx.paymentcenter.exception.point;

import cn.bootx.common.core.exception.BizException;

import static cn.bootx.paymentcenter.code.PaymentCenterErrorCode.POINT_NOT_FOUND;

/**
* @author xxm
* @date 2020/12/12
*/
public class PointNotFoundException extends BizException {
    public PointNotFoundException() {
        super(POINT_NOT_FOUND, "积分不存在.");
    }
}
