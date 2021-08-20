package cn.bootx.payment.core.pay.factory;


import cn.bootx.payment.code.pay.PayChannelCode;
import cn.bootx.payment.core.pay.func.AbsPayStrategy;
import cn.bootx.payment.core.pay.strategy.AliPayStrategy;
import cn.bootx.payment.core.pay.strategy.CashPayStrategy;
import cn.bootx.payment.core.pay.strategy.WalletPayStrategy;
import cn.bootx.payment.core.pay.strategy.WeChatPayStrategy;
import cn.bootx.payment.exception.payment.PaymentUnsupportedMethodException;
import cn.bootx.payment.param.pay.PayModeParam;
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
     * 根据传入的支付通道创建策略
     *
     * @param payModeParam 支付类型
     * @return 支付策略
     */
    public static AbsPayStrategy create(PayModeParam payModeParam) {

        AbsPayStrategy strategy = null;
        switch (payModeParam.getPayChannel()){
            case PayChannelCode.ALI:
                strategy = SpringUtil.getBean(AliPayStrategy.class);
                break;
            case PayChannelCode.WECHAT:
                strategy = SpringUtil.getBean(WeChatPayStrategy.class);
                break;
            case PayChannelCode.CASH:
                strategy = SpringUtil.getBean(CashPayStrategy.class);
                break;
            case PayChannelCode.WALLET:
                strategy = SpringUtil.getBean(WalletPayStrategy.class);
                break;
            case PayChannelCode.CREDIT:
                break;
            case PayChannelCode.VOUCHER:
                break;
            case PayChannelCode.CREDIT_CARD:
                break;
            case PayChannelCode.APPLE_PAY:
                break;
            case PayChannelCode.CHANNEL_PAY:
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
    private static List<AbsPayStrategy> create(List<PayModeParam> payModeParamList,boolean description) {
        if (CollectionUtil.isEmpty(payModeParamList)) {
            return Collections.emptyList();
        }
        List<AbsPayStrategy> list = new ArrayList<>(payModeParamList.size());

        // 同步支付
        List<PayModeParam> syncPayModelParamList = payModeParamList.stream()
                .filter(Objects::nonNull)
                .filter(payModeParam -> !PayChannelCode.SYNC_TYPE.contains(payModeParam.getPayChannel()))
                .collect(Collectors.toList());

        // 异步支付
        List<PayModeParam> asyncPayModelParamList = payModeParamList.stream()
                .filter(Objects::nonNull)
                .filter(payModeParam -> PayChannelCode.SYNC_TYPE.contains(payModeParam.getPayChannel()))
                .collect(Collectors.toList());

        List<PayModeParam> sortList = new ArrayList<>(payModeParamList.size());

        // 异步在后面
        if (description){
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
