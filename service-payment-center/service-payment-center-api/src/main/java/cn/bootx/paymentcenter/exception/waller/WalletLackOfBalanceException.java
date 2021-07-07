package cn.bootx.paymentcenter.exception.waller;


import cn.bootx.common.web.exception.BizException;
import cn.bootx.paymentcenter.code.PaymentCenterErrorCode;

/**   
* 余额不足异常
* @author xxm  
* @date 2020/12/8 
*/
public class WalletLackOfBalanceException extends BizException {
    public WalletLackOfBalanceException() {
        super(PaymentCenterErrorCode.WALLET_BALANCE_NOT_ENOUGH, "余额不足异常");
    }
}
