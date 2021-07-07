package cn.bootx.paymentcenter.core.paymodel.alipay.service;

import cn.bootx.common.web.exception.BizException;
import cn.bootx.paymentcenter.code.pay.PayTypeEnum;
import cn.bootx.paymentcenter.core.merchant.service.AppChannelService;
import cn.bootx.paymentcenter.core.paymodel.alipay.dao.AlipayConfigManager;
import cn.bootx.paymentcenter.core.paymodel.alipay.dao.AlipayConfigRepository;
import cn.bootx.paymentcenter.core.paymodel.alipay.entity.AlipayConfig;
import cn.bootx.paymentcenter.dto.paymodel.alipay.AlipayConfigDto;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.CharsetUtil;
import com.ijpay.alipay.AliPayApiConfig;
import com.ijpay.alipay.AliPayApiConfigKit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 支付宝支付
 * @author xxm
 * @date 2020/12/15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AlipayConfigService {
    private final AppChannelService appChannelService;
    private final AlipayConfigManager alipayConfigManager;
    private final AlipayConfigRepository alipayConfigRepository;

    /**
     * 添加支付宝配置
     */
    @Transactional(rollbackFor = Exception.class)
    public AlipayConfigDto add(AlipayConfigDto param){
        // 添加
        appChannelService.add(param.getAppId(),param.getState(), PayTypeEnum.ALI_PAY.getCode());
        AlipayConfig alipayConfig = AlipayConfig.init(param);
        AlipayConfig save = alipayConfigRepository.save(alipayConfig);
        return save.toDto();
    }

    /**
     * 修改
     */
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "pc:alipay:config",key = "#param.appId")
    public AlipayConfigDto update(AlipayConfigDto param){
        AlipayConfig alipayConfig = alipayConfigManager.findById(param.getId())
                .orElseThrow(() -> new BizException("支付宝不存在"));
        param.setAppId(null);
        appChannelService.update(alipayConfig.getAppId(),param.getState(), PayTypeEnum.ALI_PAY.getCode());
        BeanUtil.copyProperties(param,alipayConfig, CopyOptions.create().ignoreNullValue());
        // 支付方式
        if (CollUtil.isNotEmpty(param.getPayTypeList())){
            alipayConfig.setPayTypes(String.join(",", param.getPayTypeList()));
        } else {
            alipayConfig.setPayTypes(null);
        }
        return alipayConfigRepository.save(alipayConfig).toDto();
    }

    /**
     * 删除
     */
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "pc:alipay:config",key = "#param.appId")
    public void deleteByAppId(String appId){
        appChannelService.delete(appId,PayTypeEnum.ALI_PAY.getCode());
        alipayConfigManager.deleteByAppId(appId);
    }

    /**
     * 获取
     */
    @Cacheable(value = "pc:alipay:config",key = "#id")
    public AlipayConfigDto findById(Long id){
        return alipayConfigManager.findById(id)
                .map(AlipayConfig::toDto)
                .orElse(null);
    }

    /**
     * 获取
     */
    public AlipayConfigDto findByAppId(String appId){
        return alipayConfigManager.findByAppId(appId)
                .map(AlipayConfig::toDto)
                .orElse(null);
    }

    /**
     * 移到工具类中
     */
    public static void initApiConfig(AlipayConfig alipayConfig){
            // 支付宝支付配置注册
            AliPayApiConfig aliPayApiConfig = AliPayApiConfig.builder()
                    .setAppId(alipayConfig.getAliAppId())
                    .setPrivateKey(alipayConfig.getPrivateKey())
                    .setCharset(CharsetUtil.UTF_8)
                    .setAliPayPublicKey(alipayConfig.getAlipayPublicKey())
                    .setServiceUrl(alipayConfig.getServerUrl())
                    .setSignType(alipayConfig.getSignType())
                    .build();
        AliPayApiConfigKit.setThreadLocalAliPayApiConfig(aliPayApiConfig);
    }

}