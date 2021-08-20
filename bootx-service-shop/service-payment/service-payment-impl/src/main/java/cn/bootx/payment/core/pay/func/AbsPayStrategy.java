package cn.bootx.payment.core.pay.func;

import cn.bootx.payment.code.pay.PayChannelCode;
import cn.bootx.payment.code.pay.PaySyncStatus;
import cn.bootx.payment.core.pay.exception.ExceptionInfo;
import cn.bootx.payment.core.pay.result.PaySyncResult;
import cn.bootx.payment.core.payment.entity.Payment;
import cn.bootx.payment.param.pay.PayModeParam;
import cn.bootx.payment.param.pay.PayParam;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 抽象支付策略基类
 * 同步支付
 * 异步支付
 * 错误处理
 * 关闭支付
 * 撤销支付
 * 支付网关同步
 *
 * @author xxm
 * @date 2020/12/11
 */
@Getter
@Setter
public abstract class AbsPayStrategy {

    /** 支付对象 */
    private Payment payment = null;

    /** 支付参数 */
    private PayParam payParam = null;

    /** 支付方式参数 支付参数中的与这个不一致, 以这个为准 */
    private PayModeParam payMode = null;

    /**
     * 策略标示
     * @see PayChannelCode
     */
    public abstract int getType();

    /**
     * 初始化支付的参数
     */
    public void initPayParam(Payment payment, PayParam payParam) {
        this.payment = payment;
        this.payParam = payParam;
    }

    /**
     * 支付前处理
     */
    public void doBeforePay() {
        this.doBeforePayHandler();
    }

    /**
     * 支付前对Payment的处理
     * 包含必要的校验以及对Payment对象的创建和保存操作
     */
    protected void doBeforePayHandler(){
    }

    /**
     * 支付操作
     */
    public abstract void doPayHandler();

    /**
     * 支付成功的处理方式
     */
    public void doSuccessHandler() {
    }

    /**
     * 支付失败的处理方式
     */
    public abstract void doErrorHandler(ExceptionInfo exceptionInfo);

    /**
     * 异步支付成功的处理方式
     */
    public void doAsyncSuccessHandler(Map<String, String> map) {
    }

    /**
     * 异步支付失败的处理方式, 默认使用支付失败的处理方式
     * 同步支付方式调用时同 this#doErrorHandler
     */
    public void doAsyncErrorHandler(ExceptionInfo exceptionInfo) {
        this.doErrorHandler(exceptionInfo);
    }

    /**
     * 撤销支付操作，支付交易返回失败或支付系统超时，调用该接口撤销交易
     * 默认为关闭本地支付记录
     */
    public void doCancelHandler() {
        this.doCloseHandler();
    }

    /**
     * 关闭本地支付记录
     */
    public abstract void doCloseHandler();

    /**
     * 关闭本地以及支付网关的支付记录 用户在一定时间内未进行支付，可调用该接口直接将支付王冠中的未付款交易进行关闭
     * 默认为关闭本地支付记录
     */
    public void doCloseRemoteHandler(){
        this.doCloseHandler();
    }

    /**
     * 异步支付单与支付网关进行状态比对
     * @see PaySyncStatus
     */
    public PaySyncResult doSyncPayStatusHandler(){
        return new PaySyncResult();
    }

}
