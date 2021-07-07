package cn.bootx.paymentcenter.exception.point;

import cn.bootx.common.web.exception.BizException;

import static cn.bootx.paymentcenter.code.PaymentCenterErrorCode.POINT_COUNT_ABNORMAL;

/**
* @author xxm
* @date 2020/12/11
*/
public class PointCountAbnormalException extends BizException {
    public PointCountAbnormalException() {
        super(POINT_COUNT_ABNORMAL, "积分异常");
    }
}
