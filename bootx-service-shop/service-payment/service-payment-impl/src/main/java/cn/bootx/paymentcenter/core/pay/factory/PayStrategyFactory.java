package cn.bootx.paymentcenter.core.pay.factory;


import cn.bootx.paymentcenter.code.pay.PayTypeCode;
import cn.bootx.paymentcenter.core.pay.func.AbsPayStrategy;
import cn.bootx.paymentcenter.core.pay.strategy.*;
import cn.bootx.paymentcenter.exception.payment.PaymentUnsupportedMethodException;
import cn.bootx.paymentcenter.param.pay.PayModeParam;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.extra.spring.SpringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**   
* 支付策略工厂
* @author xxm  
* @date 2020/12/11 
*/
public class PayStrategyFactory {

    /**
     * 根据传入的支付类型创建策略
     *
     * @param payModeParam 支付类型
     * @return 支付策略
     */
    public static AbsPayStrategy create(PayModeParam payModeParam) {

        AbsPayStrategy strategy = null;
        switch (payModeParam.getType()){
            case PayTypeCode.ALI_PAY:
                strategy = SpringUtil.getBean(AliPayStrategy.class);
                break;
            case PayTypeCode.WECHAT_PAY:
                strategy = SpringUtil.getBean(WeChatPayStrategy.class);
                break;
            case PayTypeCode.CASH:
                strategy = SpringUtil.getBean(CashPayStrategy.class);
                break;
            case PayTypeCode.WALLET:
                strategy = SpringUtil.getBean(WalletPayStrategy.class);
                break;
            case PayTypeCode.POINT:
                strategy = SpringUtil.getBean(PointPayStrategy.class);
                break;
            case PayTypeCode.CREDIT:
                break;
            case PayTypeCode.VOUCHER:
                break;
            case PayTypeCode.CREDIT_CARD:
                break;
            case PayTypeCode.APPLE_PAY:
                break;
            case PayTypeCode.CHANNEL_PAY:
                break;
            default:
                throw new PaymentUnsupportedMethodException();
        }
        //noinspection ConstantConditions
        strategy.setPayMode(payModeParam);
        return strategy;
    }

    /**
     * 根据传入的支付类型批量创建策略, 异步支付在后面
     */
    public static List<AbsPayStrategy> createDesc(List<PayModeParam> payModeParamList) {
        return create(payModeParamList,true);
    }

    /**
     *  根据传入的支付类型批量创建策略, 默认异步支付在前面
     */
    public static List<AbsPayStrategy> create(List<PayModeParam> payModeParamList) {
        return create(payModeParamList,false);
    }

    /**
     * 根据传入的支付类型批量创建策略
     *
     * @param payModeParamList 支付类型
     * @return 支付策略
     */
    private static List<AbsPayStrategy> create(List<PayModeParam> payModeParamList,boolean desc) {
        if (CollectionUtil.isEmpty(payModeParamList)) {
            return Collections.emptyList();
        }
        List<AbsPayStrategy> list = new ArrayList<>(payModeParamList.size());

        // 同步支付
        List<PayModeParam> syncPayModelParamList = payModeParamList.stream()
                .filter(Objects::nonNull)
                .filter(payModeParam -> !PayTypeCode.SYNC_TYPE.contains(payModeParam.getType()))
                .collect(Collectors.toList());

        // 异步支付
        List<PayModeParam> asyncPayModelParamList = payModeParamList.stream()
                .filter(Objects::nonNull)
                .filter(payModeParam -> PayTypeCode.SYNC_TYPE.contains(payModeParam.getType()))
                .collect(Collectors.toList());

        List<PayModeParam> sortList = new ArrayList<>(payModeParamList.size());

        // 异步在后面
        if (desc){
            sortList.addAll(syncPayModelParamList);
            sortList.addAll(asyncPayModelParamList);
        } else {
            sortList.addAll(asyncPayModelParamList);
            sortList.addAll(syncPayModelParamList);
        }

        // 此处有一个根据Type的反转排序，
        sortList.stream()
                .filter(Objects::nonNull)
                .forEach(payMode -> list.add(create(payMode)));
        return list;
    }
}
