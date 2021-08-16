package cn.bootx.paymentcenter.core.pay.service;

import cn.bootx.common.util.BigDecimalUtil;
import cn.bootx.common.core.exception.BizException;
import cn.bootx.paymentcenter.code.merchant.MerchantAppCode;
import cn.bootx.paymentcenter.code.merchant.MerchantInfoCode;
import cn.bootx.paymentcenter.code.pay.PayTypeCode;
import cn.bootx.paymentcenter.core.merchant.dao.AppChannelManager;
import cn.bootx.paymentcenter.core.merchant.dao.MerchantAppManager;
import cn.bootx.paymentcenter.core.merchant.dao.MerchantInfoManager;
import cn.bootx.paymentcenter.core.merchant.entity.AppChannel;
import cn.bootx.paymentcenter.core.merchant.entity.MerchantApp;
import cn.bootx.paymentcenter.core.merchant.entity.MerchantInfo;
import cn.bootx.paymentcenter.exception.payment.PaymentAmountAbnormalException;
import cn.bootx.paymentcenter.param.pay.PayModeParam;
import cn.bootx.paymentcenter.param.pay.PayParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**   
* 支付时校验服务
* @author xxm  
* @date 2021/7/4 
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class PayValidationService {
    private final MerchantInfoManager merchantInfoManager;
    private final MerchantAppManager merchantAppManager;
    private final AppChannelManager appChannelManager;
    /**
     * 检查支付金额
     */
    public void validationAmount(List<PayModeParam> payModeList){
        for (PayModeParam payModeParam : payModeList) {
            // 同时满足支付金额小于等于零和使用数量小于等于零
            if (BigDecimalUtil.compareTo(payModeParam.getAmount(), BigDecimal.ZERO) < 1
                    && payModeParam.getCount() <= 0){
                throw new PaymentAmountAbnormalException();
            }
        }
    }

    /**
     * 检查异步支付方式
     */
    public void validationSyncPayModel(PayParam payParam) {
        // 组合支付时只允许有一个异步支付方式
        List<PayModeParam> payModeList = payParam.getPayModeList();

        long syncPayModeCount = payModeList.stream()
                .map(PayModeParam::getType)
                .filter(PayTypeCode.SYNC_TYPE::contains)
                .count();
        if (syncPayModeCount>1){
            throw new BizException("组合支付时只允许有一个异步支付方式");
        }
    }

    /**
     * 验证商户和应用配置
     */
    public void validationMerchantApp(String merchantNo, String appId, List<PayModeParam> payModeList) {
        // 1 商户是否存在和启用
        MerchantInfo merchantInfo = merchantInfoManager.findByMerchantNo(merchantNo)
                .orElseThrow(() -> new BizException("商户不存在"));
        if (Objects.equals(merchantInfo.getState(), MerchantInfoCode.STATE_OFF)){
            throw new BizException("商户未启用");
        }

        // 2 商户应用是否存在和启用
        MerchantApp merchantApp = merchantAppManager.findByMerchantNoAndAppId(merchantNo, appId)
                .orElseThrow(() -> new BizException("商户应用不存在"));
        if (Objects.equals(merchantApp.getState(), MerchantAppCode.STATE_OFF)){
            throw new BizException("商户应用未启用");
        }
        // 3 判断商户应用是否启用了指定的支付渠道
        List<AppChannel> appChannels = appChannelManager.findByAppId(merchantApp.getAppId());
        Map<Integer, AppChannel> appChannelMap = appChannels.stream()
                .collect(Collectors.toMap(AppChannel::getNo, o -> o));
        for (PayModeParam payModeParam : payModeList) {
            AppChannel appChannel = appChannelMap.get(payModeParam.getType());
            if (Objects.isNull(appChannel)||Objects.equals(appChannel.getState(),MerchantAppCode.STATE_OFF)){
                throw new BizException("支付方式不可用");
            }
        }
    }
}
