package cn.bootx.paymentcenter.utils;

import cn.bootx.paymentcenter.code.pay.PayTypeCode;

/**
*
* @author xxm
* @date 2021/2/25
*/
public interface PayTypeNameUtil {

    /**
     * 获取支付类型名称
     */
    static String getPayTypeName(int paymentType) {
        String paymentName;
        switch (paymentType) {
            case PayTypeCode.ALI_PAY:
                paymentName = "支付宝";
                break;
            case PayTypeCode.WECHAT_PAY:
                paymentName = "微信";
                break;
            case PayTypeCode.CASH:
                paymentName = "现金";
                break;
            case PayTypeCode.WALLET:
                paymentName = "钱包";
                break;
            case PayTypeCode.POINT:
                paymentName = "积分";
                break;
            case PayTypeCode.CREDIT:
                paymentName = "代金券";
                break;
            case PayTypeCode.VOUCHER:
                paymentName = "兑换券";
                break;
            case PayTypeCode.CREDIT_CARD:
                paymentName = "信用卡";
                break;
            case PayTypeCode.APPLE_PAY:
                paymentName = "Apple Pay";
                break;
            case PayTypeCode.CHANNEL_PAY:
                paymentName = "渠道方支付";
                break;
            default:
                paymentName = "未知方式";
                break;
        }
        return paymentName;
    }
}
