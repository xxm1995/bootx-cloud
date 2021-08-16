package cn.bootx.paymentcenter.core.paymodel.alipay.service;

import cn.bootx.common.core.exception.BizException;
import cn.bootx.paymentcenter.code.paymodel.AliPayCode;
import cn.bootx.paymentcenter.code.paymodel.AliPayTypeEnum;
import cn.bootx.paymentcenter.core.pay.local.SyncPayInfoLocal;
import cn.bootx.paymentcenter.core.payment.entity.Payment;
import cn.bootx.paymentcenter.core.paymodel.alipay.entity.AlipayConfig;
import cn.bootx.paymentcenter.dto.pay.AsyncPayInfo;
import cn.bootx.paymentcenter.param.paymodel.alipay.AliPayParam;
import cn.bootx.starter.headerholder.HeaderHolder;
import cn.hutool.core.util.StrUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayResponse;
import com.alipay.api.domain.*;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.ijpay.alipay.AliPayApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 支付宝支付service
 * @author xxm
 * @date 2021/2/26
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AliPayService {

    private final HeaderHolder headerHolder;

    /**
     * 支付前检查支付方式是否可用
     */
    public void validation(AliPayParam aliPayParam, AlipayConfig alipayConfig){
        List<String> payTypes = Optional.ofNullable(alipayConfig.getPayTypes())
                .filter(StrUtil::isNotBlank)
                .map(s -> StrUtil.split(s, ','))
                .orElse(new ArrayList<>(1));

        AliPayTypeEnum aliPayTypeEnum = Optional.ofNullable(AliPayTypeEnum.findByNo(aliPayParam.getPayType()))
                .orElseThrow(() -> new BizException("非法的支付宝支付类型"));
        if (!payTypes.contains(aliPayTypeEnum.getCode())) {
            throw new BizException("该支付宝支付方式不可用");
        }
    }


    /**
     * 调起支付
     */
    public void pay(BigDecimal amount, Payment payment, AliPayParam aliPayParam, AlipayConfig alipayConfig){
        String payBody = null;
        // wap支付
        if (aliPayParam.getPayType()== AliPayCode.WAP){
            payBody = this.wapPay(amount, payment,alipayConfig);
        }
        // 程序支付
        else if (aliPayParam.getPayType() == AliPayCode.APP){
            payBody = this.appPay(amount, payment, alipayConfig);
        }
        // pc支付
        else if (aliPayParam.getPayType() == AliPayCode.WEB){
            payBody = this.webPay(amount, payment, alipayConfig);
        }
        // 二维码支付
        else if (aliPayParam.getPayType() == AliPayCode.QRCODE){
            payBody = this.qrCodePay(amount, payment, alipayConfig);
        }
        // 付款码支付
        else if (aliPayParam.getPayType() == AliPayCode.BARCODE){
            this.barCode(amount, payment,aliPayParam, alipayConfig);
        }

        // payBody到线程存储
        if (StrUtil.isNotBlank(payBody)) {
            AsyncPayInfo syncPayInfo = new AsyncPayInfo()
                    .setPayBody(payBody);
            SyncPayInfoLocal.set(syncPayInfo);
        }
    }


    /**
     * wap支付
     */
    public String wapPay(BigDecimal amount, Payment payment, AlipayConfig alipayConfig){

        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();

        model.setSubject(payment.getDescription());
        model.setOutTradeNo(String.valueOf(payment.getId()));
        // 过期时间
        model.setTimeoutExpress(alipayConfig.getExpireTime());

        model.setPassbackParams(String.valueOf(headerHolder.getTid()));

        model.setTotalAmount(amount.toPlainString());
        model.setProductCode(payment.getBusinessId());

        try {
            return AliPayApi.wapPayStr(model, alipayConfig.getReturnUrl(),
                    alipayConfig.getNotifyUrl());
        }
        catch (AlipayApiException e) {
            log.error("支付宝手机支付失败", e);
            throw new BizException("支付宝手机支付失败");
        }
    }

    /**
     * app支付
     */
    public String appPay(BigDecimal amount, Payment payment, AlipayConfig alipayConfig){
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();

        model.setSubject(payment.getDescription());
        model.setOutTradeNo(String.valueOf(payment.getId()));
        // 过期时间
        model.setTimeoutExpress(alipayConfig.getExpireTime());
        model.setPassbackParams(String.valueOf(headerHolder.getTid()));
        model.setTotalAmount(amount.toPlainString());

        try {
            return AliPayApi.appPayToResponse(model, alipayConfig.getNotifyUrl()).getBody();
        }
        catch (AlipayApiException e) {
            log.error("支付宝APP支付失败", e);
            throw new BizException("支付宝APP支付失败");
        }
    }

    /**
     * PC支付
     */
    public String webPay(BigDecimal amount, Payment payment, AlipayConfig alipayConfig){

        AlipayTradePagePayModel model = new AlipayTradePagePayModel();

        model.setSubject(payment.getDescription());
        model.setOutTradeNo(String.valueOf(payment.getId()));
        model.setTimeoutExpress(alipayConfig.getExpireTime());
        model.setPassbackParams(String.valueOf(headerHolder.getTid()));
        model.setTotalAmount(amount.toPlainString());
        // 目前仅支持FAST_INSTANT_TRADE_PAY
        model.setProductCode("FAST_INSTANT_TRADE_PAY");

        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setBizModel(model);
        request.setNotifyUrl(alipayConfig.getNotifyUrl());
        request.setReturnUrl(alipayConfig.getReturnUrl());
        try {
            return AliPayApi.pageExecute(request).getBody();

        } catch (AlipayApiException e) {
            log.error("支付宝PC支付失败", e);
            throw new BizException("支付宝PC支付失败");
        }
    }

    /**
     * 二维码支付(扫码支付)
     */
    public String qrCodePay(BigDecimal amount, Payment payment, AlipayConfig alipayConfig){
        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
        model.setSubject(payment.getDescription());
        model.setOutTradeNo(String.valueOf(payment.getId()));
        model.setPassbackParams(String.valueOf(headerHolder.getTid()));
        model.setBody(String.valueOf(headerHolder.getTid()));
        model.setTotalAmount(amount.toPlainString());

        // 过期时间
        model.setTimeoutExpress(alipayConfig.getExpireTime());

        try {
            AlipayTradePrecreateResponse response = AliPayApi.tradePrecreatePayToResponse(model, alipayConfig.getNotifyUrl());
            this.verifyErrorMsg(response);
            return response.getQrCode();
        } catch (AlipayApiException e) {
            log.error("支付宝手机支付失败", e);
            throw new BizException("支付宝手机支付失败");
        }
    }

    /**
     * 付款码支付
     */
    public void barCode(BigDecimal amount, Payment payment, AliPayParam aliPayParam, AlipayConfig alipayConfig) {
        AlipayTradePayModel model = new AlipayTradePayModel();

        model.setSubject(payment.getDescription());
        model.setOutTradeNo(String.valueOf(payment.getId()));
        model.setScene("bar_code");
        model.setAuthCode(aliPayParam.getAuthCode());
        model.setPassbackParams(String.valueOf(headerHolder.getTid()));
        model.setBody(String.valueOf(headerHolder.getTid()));

        // 过期时间
        model.setTimeoutExpress(alipayConfig.getExpireTime());
        model.setTotalAmount(amount.toPlainString());

        try {
            AlipayTradePayResponse response = AliPayApi.tradePayToResponse(model, alipayConfig.getNotifyUrl());
            this.verifyErrorMsg(response);
        } catch (AlipayApiException e) {
            log.error("主动扫码支付失败", e);
            throw new BizException("主动扫码支付失败");
        }
    }

    /**
     * 验证错误信息
     */
    private void verifyErrorMsg(AlipayResponse alipayResponse){
        if (!Objects.equals(alipayResponse.getCode(),AliPayCode.SUCCESS)){
            String errorMsg = alipayResponse.getSubMsg();
            if (StrUtil.isBlank(errorMsg)){
                errorMsg = alipayResponse.getMsg();
            }
            log.error("支付失败 {}", errorMsg);
            throw new BizException(errorMsg);
        }
    }
}
