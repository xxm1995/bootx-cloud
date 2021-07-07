package cn.bootx.paymentcenter.core.pay.service;

import cn.bootx.common.web.exception.ErrorCodeRuntimeException;
import cn.bootx.paymentcenter.code.pay.PayStatusCode;
import cn.bootx.paymentcenter.code.pay.PayTypeCode;
import cn.bootx.paymentcenter.core.pay.dto.PayCallbackResult;
import cn.bootx.paymentcenter.core.pay.exception.BaseException;
import cn.bootx.paymentcenter.core.pay.exception.ExceptionInfo;
import cn.bootx.paymentcenter.core.pay.factory.PayStrategyFactory;
import cn.bootx.paymentcenter.core.pay.func.AbsPayStrategy;
import cn.bootx.paymentcenter.core.pay.func.PayStrategyConsumer;
import cn.bootx.paymentcenter.core.payment.dao.PaymentManager;
import cn.bootx.paymentcenter.core.payment.dao.PaymentRepository;
import cn.bootx.paymentcenter.core.payment.entity.Payment;
import cn.bootx.paymentcenter.core.payment.factory.PaymentFactory;
import cn.bootx.paymentcenter.dto.pay.PayResult;
import cn.bootx.paymentcenter.exception.payment.PaymentUnsupportedMethodException;
import cn.bootx.paymentcenter.mq.MessageSender;
import cn.bootx.paymentcenter.param.pay.PayParam;
import cn.hutool.core.collection.CollectionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 支付回调处理
 * @author xxm
 * @date 2021/2/27
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PayCallbackService {
    private final PaymentManager paymentManager;
    private final PaymentFactory paymentFactory;
    private final PaymentRepository paymentRepository;

    private final MessageSender messageSender;

    /**
     * 统一回调处理
     * @see PayStatusCode
     * @param tradeStatus 支付状态
     */
    public PayCallbackResult callback(Long paymentId, int tradeStatus, Map<String, String> map){

        PayCallbackResult result = new PayCallbackResult()
                .setCode(PayStatusCode.NOTIFY_PROCESS_SUCCESS);
        // 成功状态
        if (PayStatusCode.NOTIFY_TRADE_SUCCESS == tradeStatus){
            // 1. 获取payment和paymentParam数据
            Payment payment = paymentManager.findById(paymentId)
                    .orElse(null);

            // 支付单不存在,记录回调记录,后期处理
            if (Objects.isNull(payment)){
                return result.setCode(PayStatusCode.NOTIFY_PROCESS_FAIL)
                        .setMsg("支付单不存在,记录回调记录");
            }

            // payment已被取消,记录回调记录,后期处理
            if (!Objects.equals(payment.getPayStatus(),PayStatusCode.TRADE_PROGRESS)){
                return result.setCode(PayStatusCode.NOTIFY_PROCESS_FAIL)
                        .setMsg("支付单不是待支付状态,记录回调记录");
            }

            // 2.通过工厂生成对应的策略组
            PayParam payParam = paymentFactory.buildPayParamByPayment(payment);

            List<AbsPayStrategy> paymentStrategyList = PayStrategyFactory.create(payParam.getPayModeList());
            if (CollectionUtil.isEmpty(paymentStrategyList)) {
                throw new PaymentUnsupportedMethodException();
            }

            // 3.初始化支付的参数
            for (AbsPayStrategy paymentStrategy : paymentStrategyList) {
                paymentStrategy.initPayParam(payment, payParam);
            }
            // 4.处理方法, 支付时只有一种payModel(异步支付), 失败时payment的所有payModel都会生效
            boolean handlerFlag = this.doHandler(payment, paymentStrategyList, (strategyList, paymentObj) -> {
                // 执行异步支付方式的成功回调(不会有同步payModel)
                strategyList.forEach(absPaymentStrategy -> absPaymentStrategy.doAsyncSuccessHandler(map));

                // 修改payment支付状态为成功
                paymentObj.setPayStatus(PayStatusCode.TRADE_SUCCESS);
                paymentObj.setPayTime(LocalDateTime.now());
                paymentObj.setErrorCode("");
                paymentRepository.save(paymentObj);
            });

            if (handlerFlag) {
                // 5. 发送成功事件
                PayResult paymentResult = paymentFactory.buildResultByPayment(payment);
                // 成功事件
                messageSender.sendPaymentCompleted(paymentResult);
            } else {
                return result.setCode(PayStatusCode.NOTIFY_PROCESS_FAIL)
                        .setMsg("回调处理过程报错");
            }
        }
        return result;

    }

    /**
     * 处理方法
     * @param payment 支付记录
     * @param strategyList 支付策略
     * @param successCallback 成功操作
     */
    private boolean doHandler(Payment payment,
                           List<AbsPayStrategy> strategyList,
                           PayStrategyConsumer<List<AbsPayStrategy>, Payment> successCallback) {

        try {
            // 1.获取异步支付方式，通过工厂生成对应的策略组
            List<AbsPayStrategy> syncPaymentStrategyList = strategyList.stream()
                    .filter(paymentStrategy -> PayTypeCode.SYNC_TYPE.contains(paymentStrategy.getType()))
                    .collect(Collectors.toList());
            // 执行成功
            successCallback.accept(syncPaymentStrategyList, payment);
        } catch (Exception e) {
            // error事件的处理
            this.asyncErrorHandler(payment, strategyList, e);
            return false;
        }
        return true;
    }

    /**
     * 对Error的处理
     */
    private void asyncErrorHandler(Payment payment, List<AbsPayStrategy> strategyList, Exception e) {

        // 默认的错误信息
        ExceptionInfo exceptionInfo = new ExceptionInfo(PayStatusCode.TRADE_FAIL, e.getMessage());
        if (e instanceof BaseException) {
            exceptionInfo = ((BaseException) e).getExceptionInfo();
        } else if (e instanceof ErrorCodeRuntimeException) {
            ErrorCodeRuntimeException ex = (ErrorCodeRuntimeException) e;
            exceptionInfo = new ExceptionInfo(ex.getCode(), ex.getMessage());
        }

        // 更新Payment的状态
        payment.setErrorCode(String.valueOf(exceptionInfo.getErrorCode()));
        payment.setPayStatus(PayStatusCode.TRADE_FAIL);
        paymentRepository.save(payment);

        // 调用ErrorHandler
        for (AbsPayStrategy paymentStrategy : strategyList) {
            paymentStrategy.doAsyncErrorHandler(exceptionInfo);
        }
    }

}
