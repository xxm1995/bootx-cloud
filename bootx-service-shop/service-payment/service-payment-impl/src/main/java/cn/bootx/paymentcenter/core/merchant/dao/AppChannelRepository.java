package cn.bootx.paymentcenter.core.merchant.dao;

import cn.bootx.paymentcenter.core.merchant.entity.AppChannel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
* 应用支付通道配置
* @author xxm  
* @date 2021/7/1 
*/
public interface AppChannelRepository extends JpaRepository<AppChannel,Long> {
    List<AppChannel> findByAppIdAndTid(String appId, Long tid);

    boolean existsByAppIdAndCodeAndTid(String appId, String code, Long tid);

    Optional<AppChannel> findByAppIdAndCodeAndTid(String appId, String code, Long tid);

    void deleteByAppIdAndCodeAndTid(String appId, String code, Long tid);
}
