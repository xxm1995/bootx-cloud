package cn.bootx.paymentcenter.core.merchant.service;

import cn.bootx.common.core.exception.BizException;
import cn.bootx.paymentcenter.code.pay.PayTypeEnum;
import cn.bootx.paymentcenter.core.merchant.dao.AppChannelManager;
import cn.bootx.paymentcenter.core.merchant.dao.AppChannelRepository;
import cn.bootx.paymentcenter.core.merchant.dao.MerchantAppManager;
import cn.bootx.paymentcenter.core.merchant.entity.AppChannel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商户应用支付配置管理
 * @author xxm
 * @date 2021/6/29
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AppChannelService {
    private final MerchantAppManager merchantAppManager;
    private final AppChannelManager appChannelManager;
    private final AppChannelRepository appChannelRepository;

    /**
     * 添加支付支付通道
     */
    @Transactional(rollbackFor = Exception.class)
    public void add(String appId, Integer state, String code){
        if (!merchantAppManager.existsByAppId(appId)){
            throw new BizException("应用不存在");
        }
        // 判断是否存在或已经添加
        if (!appChannelManager.existsByAppId(appId,code)){
            throw new BizException("应用支付通道配置已经存在");
        }
        // 添加
        PayTypeEnum payTypeEnum = PayTypeEnum.findByCode(code);
        AppChannel appChannel = new AppChannel()
                .setAppId(appId)
                .setCode(code)
                .setNo(payTypeEnum.getNo())
                .setState(state);
        appChannelRepository.save(appChannel);
    }

    /**
     * 更新
     */
    @Transactional(rollbackFor = Exception.class)
    public void update(String appId, Integer state, String code){
        if (!merchantAppManager.existsByAppId(appId)){
            throw new BizException("应用不存在");
        }
        AppChannel appChannel = appChannelManager.findByAppIdAndCode(appId, code)
                .orElseThrow(() -> new BizException("应用支付通道配置不存在"));
        appChannel.setState(state);
        appChannelRepository.save(appChannel);
    }

    /**
     * 删除
     */
    @Transactional(rollbackFor = Exception.class)
    @Caching(evict = {
            @CacheEvict(value = "pc:merchant:app:channel",key="#appId+':'+#code"),
            @CacheEvict(value = "pc:merchant:app:channel",key="#appId")
    })
    public void delete(String appId, String code){
        appChannelManager.deleteByAppIdAndCode(appId,code);
    }

}
