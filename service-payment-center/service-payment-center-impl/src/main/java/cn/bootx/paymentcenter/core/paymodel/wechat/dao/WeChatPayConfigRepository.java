package cn.bootx.paymentcenter.core.paymodel.wechat.dao;

import cn.bootx.paymentcenter.core.paymodel.wechat.entity.WeChatPayConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
* 微信支付配置
* @author xxm
* @date 2021/3/19
*/
public interface WeChatPayConfigRepository extends JpaRepository<WeChatPayConfig,Long> {

    List<WeChatPayConfig> findAllByTid(Long tid);

    boolean existsByAppIdAndTid(String appId, Long tid);

    Optional<WeChatPayConfig> findByIdAndTid(Long id, Long tid);

    Optional<WeChatPayConfig> findByAppIdAndTid(String appId, Long tid);

    Optional<WeChatPayConfig> findByWxAppIdAndTid(String wxAppId, Long tid);

    void deleteByAppIdAndTid(String appId, Long tid);
}
