package cn.bootx.paymentcenter.core.payment.factory;

import cn.bootx.paymentcenter.code.pay.PayStatusCode;
import cn.bootx.paymentcenter.code.pay.PaymentCode;
import cn.bootx.paymentcenter.core.pay.local.SyncPayInfoLocal;
import cn.bootx.paymentcenter.core.payment.entity.Payment;
import cn.bootx.paymentcenter.dto.pay.PayResult;
import cn.bootx.paymentcenter.dto.pay.PayTypeInfo;
import cn.bootx.paymentcenter.dto.payment.PaymentDto;
import cn.bootx.paymentcenter.param.pay.PayModeParam;
import cn.bootx.paymentcenter.param.pay.PayParam;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 支付对象工厂
 * @author xxm
 * @date 2021/2/25
 */
@Component
public class PaymentFactory {

    /**
     * 构建payment记录
     */
    public Payment buildPayment(PayParam payParam){
        Payment payment = new Payment();

        // 基础信息
        payment.setBusinessId(payParam.getBusinessId())
                .setUserId(payParam.getUserId())
                .setMerchantNo(payParam.getMerchantNo())
                .setAppId(payParam.getAppId())
                .setTitle(payParam.getTitle())
                .setDescription(payParam.getDescription());

        // 支付方式和状态
        List<PayTypeInfo> payTypeInfos = this.buildPayTypeInfo(payParam.getPayModeList());
        // 计算总价
        BigDecimal sumAmount = payTypeInfos.stream()
                .map(PayTypeInfo::getAmount)
                .filter(Objects::nonNull)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        payment.setPayTypeInfo(JSONUtil.toJsonStr(payTypeInfos))
                .setPayStatus(PayStatusCode.TRADE_PROGRESS)
                .setAmount(sumAmount);
        return payment;
    }

    /**
     * 构建PayTypeInfo
     */
    private List<PayTypeInfo> buildPayTypeInfo(List<PayModeParam> payModeParamList) {
        return CollectionUtil.isEmpty(payModeParamList) ? Collections.emptyList() : payModeParamList.stream()
                .map(PayModeParam::toPayTypeInfo)
                .collect(Collectors.toList());
    }

    /**
     * 根据Payment构建PayParam支付参数
     */
    public PayParam buildPayParamByPayment(Payment payment){
        PayParam payParam = new PayParam();
        // 恢复 payModeList
        List<PayModeParam> payModeParams = payment.getPayTypeInfos().stream()
                .map(payTypeInfo -> new PayModeParam()
                        .setAmount(payTypeInfo.getAmount())
                        .setCount(payTypeInfo.getCount())
                        .setType(payTypeInfo.getType())
                        .setExtraParamsJson(payTypeInfo.getExtraParamsJson()))
                .collect(Collectors.toList());
        payParam.setPayModeList(payModeParams)
                .setBusinessId(payment.getBusinessId())
                .setUserId(payment.getUserId())
                .setMerchantNo(payment.getMerchantNo())
                .setAppId(payment.getAppId())
                .setTitle(payment.getTitle())
                .setTitle(payment.getTitle())
                .setDescription(payment.getDescription());
        return payParam;
    }


    /**
     * 根据Payment构建PaymentResult
     *
     * @param payment payment
     * @return paymentVO
     */
    public PayResult buildResultByPayment(Payment payment) {
        PayResult paymentResult = new PayResult();
        PaymentDto paymentDto = new PaymentDto();
        BeanUtils.copyProperties(payment, paymentDto);
        // 异步支付信息
        paymentResult.setSyncPayTypeCode(payment.getSyncPayTypeCode())
                .setSyncPayMode(payment.isSyncPayMode());
        paymentResult.setPayment(paymentDto);

        List<PayTypeInfo> payTypeInfos = payment.getPayTypeInfos();

        // 设置异步支付参数
        List<PayTypeInfo> moneyPayTypeInfos = payTypeInfos.stream()
                .filter(payTypeInfo -> PaymentCode.MONEY_PAY_TYPES.contains(payTypeInfo.getType()))
                .collect(Collectors.toList());
        if (!CollUtil.isEmpty(moneyPayTypeInfos)) {
            paymentResult.setSyncPayInfo(SyncPayInfoLocal.get());
        }
        // 清空线程变量
        SyncPayInfoLocal.clear();
        return paymentResult;
    }
}
