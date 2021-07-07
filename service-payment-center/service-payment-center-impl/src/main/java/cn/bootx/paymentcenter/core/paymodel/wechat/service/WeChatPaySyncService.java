package cn.bootx.paymentcenter.core.paymodel.wechat.service;

import cn.bootx.paymentcenter.code.pay.PaySyncStatus;
import cn.bootx.paymentcenter.code.paymodel.WeChatPayCode;
import cn.bootx.paymentcenter.core.pay.dto.PaySyncResult;
import cn.bootx.paymentcenter.core.paymodel.wechat.dao.WeChatPayConfigManager;
import cn.bootx.paymentcenter.core.paymodel.wechat.entity.WeChatPayConfig;
import com.ijpay.core.enums.SignType;
import com.ijpay.core.kit.WxPayKit;
import com.ijpay.wxpay.WxPayApi;
import com.ijpay.wxpay.WxPayApiConfig;
import com.ijpay.wxpay.WxPayApiConfigKit;
import com.ijpay.wxpay.model.UnifiedOrderModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 微信支付同步服务
 * @author xxm
 * @date 2021/6/21
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WeChatPaySyncService {
    private final WeChatPayConfigManager weChatPayConfigManager;

    /**
     * 同步查询
     */
    public PaySyncResult syncPayStatus(Long paymentId, WeChatPayConfig weChatPayConfig) {
        WxPayApiConfig wxPayApiConfig = WxPayApiConfigKit.getWxPayApiConfig();
        PaySyncResult paySyncResult = new PaySyncResult()
                .setPaySyncStatus(PaySyncStatus.FAIL);
        Map<String, String> params = UnifiedOrderModel
                .builder()
                .appid(wxPayApiConfig.getAppId())
                .mch_id(wxPayApiConfig.getMchId())
                .nonce_str(WxPayKit.generateStr())
                .out_trade_no(String.valueOf(paymentId))
                .build()
                .createSign(wxPayApiConfig.getApiKey(), SignType.HMACSHA256);
        try {
            String xmlResult = WxPayApi.orderQuery(params);
            Map<String, String> result = WxPayKit.xmlToMap(xmlResult);
            // 查询失败
            if (!WxPayKit.codeIsOk(result.get(WeChatPayCode.RETURN_CODE))){
                log.warn("查询微信订单失败:{}",result);
                return paySyncResult;
            }

            // 未查到订单
            if (!WxPayKit.codeIsOk(result.get(WeChatPayCode.RESULT_CODE))){
                log.warn("疑似未查询到订单:{}",result);
                return paySyncResult.setPaySyncStatus(PaySyncStatus.NOT_FOUND);
            }

            String tradeStatus = result.get(WeChatPayCode.TRADE_STATE);
            String outTradeNo = result.get(WeChatPayCode.OUT_TRADE_NO);

            // 支付完成
            if (Objects.equals(tradeStatus, WeChatPayCode.TRADE_SUCCESS)
                    ||Objects.equals(tradeStatus, WeChatPayCode.TRADE_ACCEPT)){
                HashMap<String, String> map = new HashMap<>(1);
                map.put(WeChatPayCode.OUT_TRADE_NO,outTradeNo);
                return paySyncResult.setPaySyncStatus(PaySyncStatus.TRADE_SUCCESS)
                        .setMap(map);
            }
            // 待支付
            if (Objects.equals(tradeStatus, WeChatPayCode.TRADE_NOTPAY)
                    ||Objects.equals(tradeStatus, WeChatPayCode.TRADE_USERPAYING)){
                return paySyncResult.setPaySyncStatus(PaySyncStatus.WAIT_BUYER_PAY);
            }

            // 已关闭
            if (Objects.equals(tradeStatus, WeChatPayCode.TRADE_CLOSED)
                    || Objects.equals(tradeStatus, WeChatPayCode.TRADE_REVOKED)
                    || Objects.equals(tradeStatus, WeChatPayCode.TRADE_PAYERROR)){
                return paySyncResult.setPaySyncStatus(PaySyncStatus.TRADE_CLOSED);
            }

        } catch (RuntimeException e) {
            log.error("查询订单失败:",e);
        }
        return paySyncResult;
    }
}
