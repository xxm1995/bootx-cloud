package cn.bootx.paymentcenter.core.paymodel.alipay.service;

import cn.bootx.common.core.exception.BizException;
import cn.bootx.paymentcenter.code.pay.PayStatusCode;
import cn.bootx.paymentcenter.code.pay.PayTypeCode;
import cn.bootx.paymentcenter.core.payment.dao.PaymentManager;
import cn.bootx.paymentcenter.core.payment.dao.PaymentRepository;
import cn.bootx.paymentcenter.core.payment.entity.Payment;
import cn.bootx.paymentcenter.core.paymodel.alipay.dao.AliPaymentManager;
import cn.bootx.paymentcenter.core.paymodel.alipay.dao.AliPaymentRepository;
import cn.bootx.paymentcenter.core.paymodel.alipay.entity.AliPayment;
import cn.bootx.paymentcenter.dto.pay.PayTypeInfo;
import cn.bootx.paymentcenter.param.pay.PayModeParam;
import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 支付宝支付记录
 * @author xxm
 * @date 2021/2/26
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AliPaymentService {
    private final AliPaymentManager aliPaymentManager;
    private final AliPaymentRepository aliPaymentRepository;
    private final PaymentManager paymentManager;
    private final PaymentRepository paymentRepository;

    /**
     * 支付调起成功
     * 更新 payment 中 异步支付类型信息
     */
    public void updatePaySuccess(Payment payment, PayModeParam payModeParam){
        payment.setSyncPayMode(true)
                .setSyncPayTypeCode(PayTypeCode.ALI_PAY);

        List<PayTypeInfo> payTypeInfos = payment.getPayTypeInfos();
        // 清除已有的异步支付类型信息
        payTypeInfos.removeIf(payTypeInfo -> PayTypeCode.SYNC_TYPE.contains(payTypeInfo.getType()));
        // 添加支付宝支付类型信息
        payTypeInfos.add(new PayTypeInfo()
                .setType(PayTypeCode.ALI_PAY)
                .setAmount(payModeParam.getAmount())
                .setExtraParamsJson(payModeParam.getExtraParamsJson()));
        payment.setPayTypeInfo(JSONUtil.toJsonStr(payTypeInfos));
        paymentRepository.save(payment);
    }

    /**
     * 更新支付记录错误状态
     */
    public void updateError(Long paymentId) {
        Optional<AliPayment> aliPaymentOptional = aliPaymentManager.findByPaymentId(paymentId);
        if (aliPaymentOptional.isPresent()){
            AliPayment aliPayment = aliPaymentOptional.get();
            aliPayment.setPayStatus(PayStatusCode.TRADE_FAIL);
            aliPaymentRepository.save(aliPayment);
        }
    }

    /**
     * 更新支付记录成功状态, 并创建支付宝支付记录
     */
    public void updateSyncSuccess(Long id, PayModeParam payModeParam, String tradeNo) {

        // 更新支付记录
        Payment payment = paymentManager.findById(id)
                .orElseThrow(() -> new BizException("支付记录不存在"));

        // 创建支付宝支付记录
        AliPayment aliPayment = new AliPayment();
        aliPayment.setTradeNo(tradeNo)
                .setPaymentId(payment.getId())
                .setAmount(payModeParam.getAmount())
                .setBusinessId(payment.getBusinessId())
                .setUserId(payment.getUserId())
                .setPayStatus(PayStatusCode.TRADE_SUCCESS)
                .setPayTime(LocalDateTime.now());
        aliPaymentRepository.save(aliPayment);
    }

    /**
     * 取消状态
     */
    public void updateClose(Long paymentId){
        Optional<AliPayment> aliPaymentOptional = aliPaymentManager.findByPaymentId(paymentId);
        aliPaymentOptional.ifPresent(aliPayment -> {
            aliPayment.setPayStatus(PayStatusCode.TRADE_CANCEL);
            aliPaymentRepository.save(aliPayment);
        });
    }

}
