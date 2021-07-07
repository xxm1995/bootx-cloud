package cn.bootx.paymentcenter.exception.point;

import cn.bootx.common.web.exception.FatalException;

import static cn.bootx.paymentcenter.code.PaymentCenterErrorCode.POINT_GENERATE_FAILED;

/**
* @author xxm
* @date 2020/12/11
*/
public class PointGenerateFailException extends FatalException {
    public PointGenerateFailException() {
        super(POINT_GENERATE_FAILED, "积分生成失败.");
    }
}
