package cn.bootx.paymentcenter.core.pay.strategy;

import cn.bootx.common.util.BigDecimalUtil;
import cn.bootx.common.web.exception.BizException;
import cn.bootx.paymentcenter.code.pay.PayTypeCode;
import cn.bootx.paymentcenter.code.paymodel.AliPayCode;
import cn.bootx.paymentcenter.core.pay.dto.PaySyncResult;
import cn.bootx.paymentcenter.core.pay.exception.ExceptionInfo;
import cn.bootx.paymentcenter.core.pay.func.AbsPayStrategy;
import cn.bootx.paymentcenter.core.paymodel.alipay.dao.AlipayConfigManager;
import cn.bootx.paymentcenter.core.paymodel.alipay.entity.AlipayConfig;
import cn.bootx.paymentcenter.core.paymodel.alipay.service.*;
import cn.bootx.paymentcenter.exception.payment.PayAmountAbnormalException;
import cn.bootx.paymentcenter.param.pay.PayModeParam;
import cn.bootx.paymentcenter.param.paymodel.alipay.AliPayParam;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

/**
 * 支付宝支付
 * @author xxm
 * @date 2021/2/27
 */
@Scope(SCOPE_PROTOTYPE)
@Component
@RequiredArgsConstructor
public class AliPayStrategy extends AbsPayStrategy {

    private final AliPaymentService aliPaymentService;
    private final AlipaySyncService alipaySyncService;
    private final AliPayService aliPayService;
    private final AliPayCancelService aliPayCancelService;
    private final AlipayConfigManager alipayConfigManager;

    private AlipayConfig alipayConfig;
    private AliPayParam aliPayParam;


    @Override
    public int getType() {
        return PayTypeCode.ALI_PAY;
    }

    /**
     * 支付前操作
     */
    @Override
    public void doBeforePayHandler(){
        try {
            // 支付宝参数验证
            this.aliPayParam = JSONUtil.toBean(this.getPayMode().getExtraParamsJson(), AliPayParam.class);
        } catch (JSONException e) {
            throw new BizException("支付参数错误");
        }
        // 检查金额
        PayModeParam payMode = this.getPayMode();
        if (BigDecimalUtil.compareTo(payMode.getAmount(), BigDecimal.ZERO) < 1){
            throw new PayAmountAbnormalException();
        }
        // 检查并获取支付宝支付配置
        this.alipayConfig = alipayConfigManager.findByAppId(this.getPayParam().getAppId())
                .orElseThrow(() -> new BizException("支付配置不存在"));
        aliPayService.validation(aliPayParam,alipayConfig);
        AlipayConfigService.initApiConfig(alipayConfig);
    }

    /**
     * 发起支付操作
     */
    @Override
    public void doPayHandler() {
        aliPayService.pay(this.getPayMode().getAmount(),
                this.getPayment(),
                this.aliPayParam,
                this.alipayConfig);
    }

    /**
     * 支付调起成功
     */
    @Override
    public void doSuccessHandler(){
        aliPaymentService.updatePaySuccess(this.getPayment(),this.getPayMode());
    }

    /**
     * 发起支付失败
     */
    @Override
    public void doErrorHandler(ExceptionInfo exceptionInfo) {
        this.doCloseHandler();
    }

    /**
     * 异步支付成功
     */
    @Override
    public void doAsyncSuccessHandler(Map<String, String> map) {
        String tradeNo = map.get(AliPayCode.TRADE_NO);
        aliPaymentService.updateSyncSuccess(this.getPayment().getId(),this.getPayMode(),tradeNo);
    }

    /**
     * 异步支付失败
     */
    @Override
    public void doAsyncErrorHandler(ExceptionInfo exceptionInfo) {
        // 调用撤销支付
        this.doCancelHandler();
    }

    /**
     * 撤销支付
     */
    @Override
    public void doCancelHandler() {
        this.alipayConfig = Optional.ofNullable(this.alipayConfig)
                .orElse(alipayConfigManager.findByAppId(this.getPayParam().getAppId())
                        .orElseThrow(() -> new BizException("支付配置不存在")));
        aliPayCancelService.cancelRemote(this.getPayment(),alipayConfig);
        AlipayConfigService.initApiConfig(alipayConfig);
        // 调用关闭本地交易单
        this.doCloseHandler();
    }

    /**
     * 关闭本地交易单
     */
    @Override
    public void doCloseHandler() {
        aliPaymentService.updateClose(this.getPayment().getId());
    }

    /**
     * 异步支付单与支付网关进行状态比对
     */
    @Override
    public PaySyncResult doSyncPayStatusHandler(){
        this.alipayConfig = alipayConfigManager.findByAppId(this.getPayParam().getAppId())
                .orElseThrow(() -> new BizException("支付配置不存在"));
        AlipayConfigService.initApiConfig(alipayConfig);
        return alipaySyncService.syncPayStatus(this.getPayment(),alipayConfig);
    }
}
