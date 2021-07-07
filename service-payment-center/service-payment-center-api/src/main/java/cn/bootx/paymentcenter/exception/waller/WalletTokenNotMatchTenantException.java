package cn.bootx.paymentcenter.exception.waller;


import cn.bootx.common.web.exception.BizException;
import cn.bootx.paymentcenter.code.PaymentCenterErrorCode;

/**   
* 钱包Token不支持当前租户
* @author xxm  
* @date 2020/12/8 
*/
public class WalletTokenNotMatchTenantException extends BizException {

    public WalletTokenNotMatchTenantException() {
        super(PaymentCenterErrorCode.WALLET_TOKEN_MATCH_TENANT_ERROR, "钱包Token不支持当前租户");
    }
}
