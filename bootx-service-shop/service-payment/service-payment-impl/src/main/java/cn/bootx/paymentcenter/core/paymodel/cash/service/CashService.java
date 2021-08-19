package cn.bootx.paymentcenter.core.paymodel.cash.service;

import cn.bootx.paymentcenter.code.pay.PayStatusCode;
import cn.bootx.paymentcenter.core.payment.entity.Payment;
import cn.bootx.paymentcenter.core.paymodel.cash.dao.CashPaymentManager;
import cn.bootx.paymentcenter.core.paymodel.cash.dao.CashPaymentRepository;
import cn.bootx.paymentcenter.core.paymodel.cash.entity.CashPayment;
import cn.bootx.paymentcenter.param.pay.PayModeParam;
import cn.bootx.paymentcenter.param.pay.PayParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 现金支付
 * @author xxm
 * @date 2021/6/23
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CashService {
    private final CashPaymentRepository cashPaymentRepository;
    private final CashPaymentManager cashPaymentManager;

    /**
     * 支付
     */
    public void pay(PayModeParam payMode, Payment payment, PayParam payParam) {
        CashPayment walletPayment = new CashPayment();
        walletPayment
                .setPaymentId(payment.getId())
                .setUserId(payment.getUserId())
                .setBusinessId(payParam.getBusinessId())
                .setAmount(payMode.getAmount())
                .setPayStatus(payment.getPayStatus());
        cashPaymentRepository.save(walletPayment);
    }

    /**
     * 关闭
     */
    public void close(Long paymentId) {
        Optional<CashPayment> cashPaymentOpt = cashPaymentManager.findByPaymentId(paymentId);
        cashPaymentOpt.ifPresent(cashPayment -> {
            cashPayment.setPayStatus(PayStatusCode.TRADE_CANCEL);
            cashPaymentRepository.save(cashPayment);
        });
    }
}
