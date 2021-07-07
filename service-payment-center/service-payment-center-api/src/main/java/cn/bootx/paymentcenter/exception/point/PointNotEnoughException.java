package cn.bootx.paymentcenter.exception.point;

import static cn.bootx.paymentcenter.code.PaymentCenterErrorCode.POINT_NOT_ENOUGH;

/**
* @author xxm
* @date 2020/12/11
*/
public class PointNotEnoughException extends PointPayFailureException{

    public PointNotEnoughException() {
        super(POINT_NOT_ENOUGH, "积分不够.");
    }

}
