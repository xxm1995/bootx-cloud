package cn.bootx.paymentcenter.core.payconfig.dao;

import cn.bootx.paymentcenter.core.payconfig.entity.PayChannelWay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PayChannelWayRepository extends JpaRepository<PayChannelWay,Long> {
    boolean existsByChannelIdAndCodeAndTid(Long channelId, String code, Long tid);

    List<PayChannelWay> findByChannelIdAndTid(Long channelId, Long tid);

    void deleteByIdAndTid(Long id, Long tid);

    Optional<PayChannelWay> findByIdAndTid(Long id, Long tid);
}
