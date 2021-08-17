package cn.bootx.paymentcenter.core.merchant.dao;

import cn.bootx.paymentcenter.core.merchant.entity.AppChannel;
import cn.bootx.common.headerholder.HeaderHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
* 应用支付通道配置
* @author xxm
* @date 2021/7/1
*/
@Slf4j
@Repository
@RequiredArgsConstructor
public class AppChannelManager {
    private final AppChannelRepository repository;
    private final HeaderHolder headerHolder;

    @Cacheable(value = "pc:merchant:app:channel",key = "#appId")
    public List<AppChannel> findByAppId(String appId) {
        return repository.findByAppIdAndTid(appId,headerHolder.findTid());
    }

    @Cacheable(value = "pc:merchant:app:channel",key = "#appId+':'+#code")
    public Optional<AppChannel> findByAppIdAndCode(String appId, String code) {
        return repository.findByAppIdAndCodeAndTid(appId,code,headerHolder.findTid());
    }

    public boolean existsByAppId(String appId, String code) {
        return repository.existsByAppIdAndCodeAndTid(appId,code,headerHolder.findTid());
    }

    public void deleteByAppIdAndCode(String appId, String code) {
        repository.deleteByAppIdAndCodeAndTid(appId,code,headerHolder.findTid());
    }
}
