package cn.bootx.paymentcenter.core.paymodel.wechat.service;

import cn.bootx.common.web.exception.BizException;
import cn.bootx.paymentcenter.code.pay.PayTypeEnum;
import cn.bootx.paymentcenter.core.merchant.service.AppChannelService;
import cn.bootx.paymentcenter.core.paymodel.wechat.dao.WeChatPayConfigManager;
import cn.bootx.paymentcenter.core.paymodel.wechat.dao.WeChatPayConfigRepository;
import cn.bootx.paymentcenter.core.paymodel.wechat.entity.WeChatPayConfig;
import cn.bootx.paymentcenter.dto.paymodel.wechat.WeChatPayConfigDto;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import com.ijpay.wxpay.WxPayApiConfig;
import com.ijpay.wxpay.WxPayApiConfigKit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* 微信支付配置
* @author xxm
* @date 2021/3/5
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class WeChatPayConfigService {
    private final AppChannelService appChannelService;
    private final WeChatPayConfigRepository weChatPayConfigRepository;
    private final WeChatPayConfigManager weChatPayConfigManager;

    /**
     * 添加微信支付配置
     */
    @Transactional(rollbackFor = Exception.class)
    public WeChatPayConfigDto add(WeChatPayConfigDto param){
        appChannelService.add(param.getAppId(),param.getState(), PayTypeEnum.WECHAT_PAY.getCode());
        WeChatPayConfig weChatPayConfig = WeChatPayConfig.init(param);
        WeChatPayConfig save = weChatPayConfigRepository.save(weChatPayConfig);
        return save.toDto();
    }

    /**
     * 修改
     */
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "pc:wx:config",key = "#param.appId")
    public WeChatPayConfigDto update(WeChatPayConfigDto param){
        WeChatPayConfig weChatPayConfig = weChatPayConfigManager.findById(param.getId())
                .orElseThrow(() -> new BizException("支付宝不存在"));
        appChannelService.update(weChatPayConfig.getAppId(),param.getState(), PayTypeEnum.ALI_PAY.getCode());
        BeanUtil.copyProperties(param,weChatPayConfig, CopyOptions.create().ignoreNullValue());
        // 支付方式
        if (CollUtil.isNotEmpty(param.getPayTypeList())){
            weChatPayConfig.setPayTypes(String.join(",", param.getPayTypeList()));
        } else {
            weChatPayConfig.setPayTypes(null);
        }
        return weChatPayConfigRepository.save(weChatPayConfig).toDto();
    }

    /**
     * 删除
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteByAppId(String appId){
        appChannelService.delete(appId,PayTypeEnum.ALI_PAY.getCode());
        weChatPayConfigManager.deleteByAppId(appId);
    }

    /**
     * 获取
     */
    public WeChatPayConfigDto findById(Long id){
        return weChatPayConfigManager.findById(id)
                .map(WeChatPayConfig::toDto)
                .orElse(null);
    }

    /**
     * 获取
     */
    public WeChatPayConfigDto findByAppId(String appId){
        return weChatPayConfigManager.findByAppId(appId)
                .map(WeChatPayConfig::toDto)
                .orElse(null);
    }

    /**
     * 初始化
     */
    public static void initPayConfig(WeChatPayConfig weChatPayConfig) {
            WxPayApiConfig wxPayApiConfig = WxPayApiConfig.builder()
                    .appId(weChatPayConfig.getWxAppId())
                    .mchId(weChatPayConfig.getMchId())
                    .apiKey(weChatPayConfig.getApiKey())
                    .certPath(weChatPayConfig.getCertPath())
                    .domain(weChatPayConfig.getDomain())
                    .build();
            WxPayApiConfigKit.setThreadLocalWxPayApiConfig(wxPayApiConfig);
    }
}
