package cn.bootx.paymentcenter.core.payconfig.dao;

import cn.bootx.paymentcenter.core.payconfig.entity.PayChannelWay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayChannelWayRepository extends JpaRepository<PayChannelWay,Long> {
    boolean existsByChannelIdAndCodeAndTid(Long channelId, String code, Long tid);

    List<PayChannelWay> findByChannelIdAndTid(Long channelId, Long tid);

    void deleteByIdAndTid(Long id, Long tid);
}
