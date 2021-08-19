package cn.bootx.paymentcenter.code.pay;

import java.util.*;

/**
 * 支付中心应用常量
 *
 * @author xxm
 * @date 2021/2/24
 */
public interface PaymentCode {

    /**
     * 可退款的支付方式 1.支付宝 2.微信 4.钱包 5.ApplePay
     */
    List<Integer> MONEY_PAY_TYPES = Collections.unmodifiableList(Arrays.asList(
            PayTypeCode.ALI_PAY,
            PayTypeCode.WECHAT_PAY,
            PayTypeCode.WALLET,
            PayTypeCode.APPLE_PAY
    ));


    /**
     * 1.正式
     * 2.沙盒
     */
    int PAY_ENV_PRODUCTION = 1;
    int PAY_ENV_SANDBOX = 2;


}
