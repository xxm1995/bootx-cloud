package cn.bootx.paymentcenter.exception.waller;

import cn.bootx.common.core.exception.BizException;
import cn.bootx.paymentcenter.code.PaymentCenterErrorCode;

/**   
* 钱包不存在
* @author xxm  
* @date 2020/12/8 
*/
public class WalletNotExistsException extends BizException {

    public WalletNotExistsException() {
        super(PaymentCenterErrorCode.WALLET_NOT_EXISTS, "wallet not exists");
    }
}
