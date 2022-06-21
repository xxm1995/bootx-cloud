package cn.bootx.paymentcenter.core.paymodel.wechat.dao;

import cn.bootx.paymentcenter.core.paymodel.wechat.entity.WeChatPayConfig;
import cn.bootx.starter.headerholder.HeaderHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**   
* 微信支付配置
* @author xxm  
* @date 2021/3/19 
*/
@Repository
@RequiredArgsConstructor
public class WeChatPayConfigManager {
    private final WeChatPayConfigRepository repository;

    private final HeaderHolder headerHolder;

    /**
     * 查询全部
     */
    public List<WeChatPayConfig> findAll() {
        return repository.findAllByTid(headerHolder.findTid());
    }

    public Optional<WeChatPayConfig> findById(Long id) {
        return repository.findByIdAndTid(id,headerHolder.findTid());
    }

    @Cacheable(value = "pc:wx:config:appid",key = "#appId")
    public Optional<WeChatPayConfig> findByAppId(String appId) {
        return repository.findByAppIdAndTid(appId,headerHolder.findTid());
    }
    @Cacheable(value = "pc:wx:config:wx",key = "#wxAppId")
    public Optional<WeChatPayConfig> findByWxAppId(String wxAppId) {
        return repository.findByWxAppIdAndTid(wxAppId,headerHolder.findTid());
    }

    public boolean existsByAppId(String appId) {
        return repository.existsByAppIdAndTid(appId,headerHolder.findTid());
    }

    public void deleteByAppId(String appId) {
        repository.deleteByAppIdAndTid(appId,headerHolder.findTid());
    }
}
