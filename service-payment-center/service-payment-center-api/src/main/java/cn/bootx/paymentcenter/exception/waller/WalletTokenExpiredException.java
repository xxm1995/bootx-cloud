package cn.bootx.paymentcenter.exception.waller;

import cn.bootx.common.web.exception.BizException;
import cn.bootx.paymentcenter.code.PaymentCenterErrorCode;

/**   
* 钱包Token已过期
* @author xxm  
* @date 2020/12/8 
*/
public class WalletTokenExpiredException extends BizException {

    public WalletTokenExpiredException() {
        super(PaymentCenterErrorCode.WALLET_TOKEN_EXPIRED, "钱包Token已过期");
    }
}
