package cn.bootx.paymentcenter.exception.point;

import cn.bootx.common.core.exception.BizException;

import static cn.bootx.paymentcenter.code.PaymentCenterErrorCode.POINT_RESTORE_ERROR;

/**   
* 积分返还异常
* @author xxm  
* @date 2020/12/11 
*/
public class PointRestoreException extends BizException {

    public PointRestoreException() {
        super(POINT_RESTORE_ERROR, "积分返还异常");
    }
}
