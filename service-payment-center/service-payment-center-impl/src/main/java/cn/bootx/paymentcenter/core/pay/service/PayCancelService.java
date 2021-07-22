package cn.bootx.paymentcenter.core.pay.service;

import cn.bootx.common.web.exception.BizException;
import cn.bootx.paymentcenter.code.pay.PayStatusCode;
import cn.bootx.paymentcenter.core.pay.factory.PayStrategyFactory;
import cn.bootx.paymentcenter.core.payment.dao.PaymentManager;
import cn.bootx.paymentcenter.core.payment.dao.PaymentRepository;
import cn.bootx.paymentcenter.core.payment.entity.Payment;
import cn.bootx.paymentcenter.core.payment.factory.PaymentFactory;
import cn.bootx.paymentcenter.core.pay.func.AbsPayStrategy;
import cn.bootx.paymentcenter.core.pay.func.PayStrategyConsumer;
import cn.bootx.paymentcenter.core.payment.service.PaymentService;
import cn.bootx.paymentcenter.exception.payment.PaymentUnsupportedMethodException;
import cn.bootx.paymentcenter.param.pay.PayParam;
import cn.hutool.core.collection.CollectionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 取消订单处理
 * @author xxm
 * @date 2021/3/2
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PayCancelService {
    private final PaymentFactory paymentFactory;
    private final PaymentManager paymentManager;
    private final PaymentService paymentService;
    private final PaymentRepository paymentRepository;

    /**
     * 根据业务id取消支付记录
     */
    @Transactional(rollbackFor = Exception.class)
    public void cancelByBusinessId(String businessId) {
        Optional<Payment> paymentOptional = Optional.ofNullable(paymentService.getByBusinessId(businessId));
        paymentOptional.ifPresent(this::cancelPayment);
    }


    /**
     * 根据paymentId取消支付记录
     */
    @Transactional(rollbackFor = Exception.class)
    public void cancelByPaymentId(Long paymentId){
        // 获取payment和paymentParam数据
        Payment payment = paymentManager.findById(paymentId)
                .orElseThrow(() -> new BizException("未找到payment"));
        this.cancelPayment(payment);
    }

    /**
     * 取消支付记录
     */
    private void cancelPayment(Payment payment){

        // 获取 paymentParam
        PayParam payParam = paymentFactory.buildPayParamByPayment(payment);;

        // 1.获取支付方式，通过工厂生成对应的策略组
        List<AbsPayStrategy> paymentStrategyList = PayStrategyFactory.create(payParam.getPayModeList());
        if (CollectionUtil.isEmpty(paymentStrategyList)) {
            throw new PaymentUnsupportedMethodException();
        }

        // 2.初始化支付的参数
        for (AbsPayStrategy paymentStrategy : paymentStrategyList) {
            paymentStrategy.initPayParam(payment, payParam);
        }

        // 3.执行取消订单
        this.doHandler(payment,paymentStrategyList,(strategyList, paymentObj) -> {
            // 发起支付成功进行的执行方法
            strategyList.forEach(AbsPayStrategy::doCancelHandler);
            // 取消订单
            paymentObj.setPayStatus(PayStatusCode.TRADE_CANCEL);
            paymentObj.setErrorCode("");
            paymentRepository.save(paymentObj);
        });
    }

    /**
     * 处理方法
     * @param payment 支付记录
     * @param strategyList 支付策略
     * @param successCallback 成功操作
     */
    private void doHandler(Payment payment,
                           List<AbsPayStrategy> strategyList,
                           PayStrategyConsumer<List<AbsPayStrategy>, Payment> successCallback) {

        try {
            // 执行
            successCallback.accept(strategyList, payment);
        } catch (Exception e) {
            // error事件的处理
            this.errorHandler(payment, strategyList, e);
            throw e;
        }
    }

    /**
     * 对Error的处理
     */
    private void errorHandler(Payment payment, List<AbsPayStrategy> strategyList, Exception e) {
        // 待编写
    }
}
