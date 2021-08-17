package cn.bootx.paymentcenter.core.paymodel.wechat.service;

import cn.bootx.common.core.exception.BizException;
import cn.bootx.paymentcenter.code.paymodel.WeChatPayCode;
import cn.bootx.paymentcenter.code.paymodel.WeChatPayTypeEnum;
import cn.bootx.paymentcenter.core.pay.local.SyncPayInfoLocal;
import cn.bootx.paymentcenter.core.payment.entity.Payment;
import cn.bootx.paymentcenter.core.paymodel.wechat.entity.WeChatPayConfig;
import cn.bootx.paymentcenter.dto.pay.AsyncPayInfo;
import cn.bootx.paymentcenter.param.paymodel.wechat.WeChatPayParam;
import cn.bootx.common.headerholder.HeaderHolder;
import cn.hutool.core.util.StrUtil;
import com.alibaba.nacos.api.utils.NetUtils;
import com.ijpay.core.enums.SignType;
import com.ijpay.core.enums.TradeType;
import com.ijpay.core.kit.WxPayKit;
import com.ijpay.wxpay.WxPayApi;
import com.ijpay.wxpay.WxPayApiConfig;
import com.ijpay.wxpay.WxPayApiConfigKit;
import com.ijpay.wxpay.model.MicroPayModel;
import com.ijpay.wxpay.model.UnifiedOrderModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
* 微信支付
* @author xxm
* @date 2021/3/2
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class WeChatPayService {
    private final HeaderHolder headerHolder;

    /**
     * 校验
     */
    public void validation(WeChatPayParam weChatPayParam, WeChatPayConfig weChatPayConfig) {
        List<String> payTypes = Optional.ofNullable(weChatPayConfig.getPayTypes())
                .filter(StrUtil::isNotBlank)
                .map(s -> StrUtil.split(s, ','))
                .orElse(new ArrayList<>(1));

        WeChatPayTypeEnum weChatPayTypeEnum = Optional.ofNullable(WeChatPayTypeEnum.findByNo(weChatPayParam.getPayType()))
                .orElseThrow(() -> new BizException("非法的微信支付类型"));
        if (!payTypes.contains(weChatPayTypeEnum.getCode())) {
            throw new BizException("该微信支付方式不可用");
        }
    }

    /**
     * 支付
     */
    public void pay(BigDecimal amount, Payment payment, WeChatPayParam weChatPayParam, WeChatPayConfig weChatPayConfig){

        String payBody = null;

        // wap支付
        if (weChatPayParam.getPayType() == WeChatPayCode.WAP){
            payBody = this.wapPay(amount, payment,weChatPayConfig);
        }
        // 程序支付
        else if (weChatPayParam.getPayType() == WeChatPayCode.APP){
            payBody = this.appPay(amount, payment, weChatPayConfig);
        }
        // 微信公众号支付或者小程序支付
        else if (weChatPayParam.getPayType() == WeChatPayCode.JSAPI){
            payBody = this.jsPay(amount, payment,weChatPayParam.getOpenId(), weChatPayConfig);
        }
        // 二维码支付
        else if (weChatPayParam.getPayType() == WeChatPayCode.QRCODE){
            payBody = this.qrCodePay(amount, payment, weChatPayConfig);
        }
        // 付款码支付
        else if (weChatPayParam.getPayType() == WeChatPayCode.BARCODE){
            this.barCode(amount, payment, weChatPayParam.getAuthCode(),weChatPayParam, weChatPayConfig);
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
    private String wapPay(BigDecimal amount, Payment payment, WeChatPayConfig weChatPayConfig) {
        WxPayApiConfig wxPayApiConfig = WxPayApiConfigKit.getWxPayApiConfig();
        Map<String, String> params = this.buildParams(amount,payment,weChatPayConfig,TradeType.MWEB.getTradeType())
                .build()
                .createSign(wxPayApiConfig.getApiKey(), SignType.HMACSHA256);

        String xmlResult = WxPayApi.pushOrder(false, params);
        Map<String, String> result = WxPayKit.xmlToMap(xmlResult);
        this.verifyErrorMsg(result);
        return result.get(WeChatPayCode.MWEB_URL);
    }

    /**
     * 程序支付
     */
    private String appPay(BigDecimal amount, Payment payment, WeChatPayConfig weChatPayConfig) {
        WxPayApiConfig wxPayApiConfig = WxPayApiConfigKit.getWxPayApiConfig();
        Map<String, String> params = this.buildParams(amount,payment,weChatPayConfig,TradeType.APP.getTradeType())
                .build()
                .createSign(wxPayApiConfig.getApiKey(), SignType.HMACSHA256);

        String xmlResult = WxPayApi.pushOrder(false, params);
        Map<String, String> result = WxPayKit.xmlToMap(xmlResult);
        this.verifyErrorMsg(result);
        return result.get(WeChatPayCode.PREPAY_ID);
    }


    /**
     * 微信公众号支付或者小程序支付
     */
    private String jsPay(BigDecimal amount, Payment payment, String openId, WeChatPayConfig weChatPayConfig) {
        WxPayApiConfig wxPayApiConfig = WxPayApiConfigKit.getWxPayApiConfig();
        Map<String, String> params = this.buildParams(amount,payment,weChatPayConfig,TradeType.JSAPI.getTradeType())
                .openid(openId)
                .build()
                .createSign(wxPayApiConfig.getApiKey(), SignType.HMACSHA256);

        String xmlResult = WxPayApi.pushOrder(false, params);
        Map<String, String> result = WxPayKit.xmlToMap(xmlResult);
        this.verifyErrorMsg(result);
        return result.get(WeChatPayCode.PREPAY_ID);
    }

    /**
     * 二维码支付
     */
    private String qrCodePay(BigDecimal amount, Payment payment, WeChatPayConfig weChatPayConfig) {

        WxPayApiConfig wxPayApiConfig = WxPayApiConfigKit.getWxPayApiConfig();
        Map<String, String> params = this.buildParams(amount,payment,weChatPayConfig,TradeType.NATIVE.getTradeType())
                .build()
                .createSign(wxPayApiConfig.getApiKey(), SignType.HMACSHA256);

        String xmlResult = WxPayApi.pushOrder(false, params);
        Map<String, String> result = WxPayKit.xmlToMap(xmlResult);
        this.verifyErrorMsg(result);
        return result.get(WeChatPayCode.CODE_URL);
    }

    /**
     * 条形码支付
     */
    private void barCode(BigDecimal amount, Payment payment, String authCode, WeChatPayParam weChatPayParam, WeChatPayConfig weChatPayConfig) {
        String totalFee = String.valueOf(amount.multiply(new BigDecimal(100)).longValue());
        WxPayApiConfig wxPayApiConfig = WxPayApiConfigKit.getWxPayApiConfig();
        Map<String, String> params = MicroPayModel
                .builder()
                .appid(wxPayApiConfig.getAppId())
                .mch_id(wxPayApiConfig.getMchId())
                .nonce_str(WxPayKit.generateStr())
                .body(payment.getTitle())
                .auth_code(authCode)
                .attach(String.valueOf(headerHolder.getTid()))
                .out_trade_no(String.valueOf(payment.getId()))
                .total_fee(totalFee)
                .spbill_create_ip(NetUtils.localIP())
                .build()
                .createSign(wxPayApiConfig.getApiKey(), SignType.HMACSHA256);

        String xmlResult = WxPayApi.pushOrder(false, params);
        Map<String, String> result = WxPayKit.xmlToMap(xmlResult);
        this.verifyErrorMsg(result);
        String tradeType = result.get(WeChatPayCode.TRADE_TYPE);
        // 支付成功
        if (Objects.equals(tradeType, WeChatPayCode.BARCODE)) {
            // 发布事件
        } else {
            // 发布事件
        }
    }

    /**
     * 构建参数
     */
    private UnifiedOrderModel.UnifiedOrderModelBuilder buildParams(BigDecimal amount,
                                                                   Payment payment,
                                                                   WeChatPayConfig weChatPayConfig,
                                                                   String tradeType){
        String totalFee = String.valueOf(amount.multiply(new BigDecimal(100)).longValue());
        WxPayApiConfig wxPayApiConfig = WxPayApiConfigKit.getWxPayApiConfig();

        return UnifiedOrderModel
                .builder()
                .appid(wxPayApiConfig.getAppId())
                .mch_id(wxPayApiConfig.getMchId())
                .nonce_str(WxPayKit.generateStr())
                .body(payment.getTitle())
                .attach(String.valueOf(headerHolder.getTid()))
                .out_trade_no(String.valueOf(payment.getId()))
                .total_fee(totalFee)
                .spbill_create_ip(NetUtils.localIP())
                .notify_url(weChatPayConfig.getNotifyUrl())
                .trade_type(tradeType);
    }


    /**
     * 验证错误信息
     */
    private void verifyErrorMsg(Map<String, String> result){
        String returnCode = result.get(WeChatPayCode.RETURN_CODE);
        String resultCode = result.get(WeChatPayCode.RESULT_CODE);
        if (!WxPayKit.codeIsOk(returnCode)||!WxPayKit.codeIsOk(resultCode)) {
            String errorMsg = result.get(WeChatPayCode.ERR_CODE_DES);
            if (StrUtil.isBlank(errorMsg)){
                errorMsg = result.get(WeChatPayCode.RETURN_MSG);
            }
            log.error("支付失败 {}", errorMsg);
            throw new BizException(errorMsg);
        }
    }

}
