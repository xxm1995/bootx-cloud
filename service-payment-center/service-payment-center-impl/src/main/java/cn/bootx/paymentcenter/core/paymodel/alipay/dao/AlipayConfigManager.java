package cn.bootx.paymentcenter.core.paymodel.alipay.dao;

import cn.bootx.paymentcenter.core.paymodel.alipay.entity.AlipayConfig;
import cn.bootx.starter.headerholder.HeaderHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
* 阿里支付配置
* @author xxm
* @date 2021/2/26
*/
@Repository
@RequiredArgsConstructor
public class AlipayConfigManager {
    private final AlipayConfigRepository repository;
    private final HeaderHolder headerHolder;

    public Optional<AlipayConfig> findById(Long id){
        return repository.findByIdAndTid(id,headerHolder.findTid());
    }

    /**
     * 根据商户应用AppId获取支付宝支付配置
     */
    @Cacheable(value = "pc:alipay:config",key = "#appId")
    public Optional<AlipayConfig> findByAppId(String appId){
        return repository.findByAppIdAndTid(appId,headerHolder.findTid());
    }

    /**
     * 查询全部
     */
    public List<AlipayConfig> findAll() {
        return repository.findAllByTid(headerHolder.findTid());
    }

    public boolean existsByAppId(String appId) {
        return repository.existsByAppIdAndTid(appId,headerHolder.findTid());
    }

    public void deleteByAppId(String appId) {
        repository.deleteByAppIdAndTid(appId,headerHolder.findTid());
    }
}
