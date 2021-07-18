package cn.bootx.paymentcenter.core.paymodel.alipay.dao;

import cn.bootx.paymentcenter.core.paymodel.alipay.entity.AlipayConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlipayConfigRepository extends JpaRepository<AlipayConfig,Long> {

    List<AlipayConfig> findAllByTid(Long tid);

    boolean existsByAppIdAndTid(String appId, Long tid);

    Optional<AlipayConfig> findByIdAndTid(Long id, Long tid);

    Optional<AlipayConfig> findByAppIdAndTid(String appId, Long tid);

    Optional<AlipayConfig> findByAliAppIdAndTid(String aliAppId, Long tid);

    void deleteByAppIdAndTid(String appId, Long tid);
}
