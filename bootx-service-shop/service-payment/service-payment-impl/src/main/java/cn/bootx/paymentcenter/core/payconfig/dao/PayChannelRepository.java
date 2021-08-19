package cn.bootx.paymentcenter.core.payconfig.dao;

import cn.bootx.paymentcenter.core.payconfig.entity.PayChannel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
* 支付通道
* @author xxm  
* @date 2021/6/30 
*/
public interface PayChannelRepository extends JpaRepository<PayChannel,Long> {
    boolean existsByCodeAndTid(String code, Long tid);

    boolean existsByIdAndTid(Long id, Long tid);

    Optional<PayChannel> findByIdAndTid(Long id, Long tid);

    Optional<PayChannel> findByCodeAndTid(String code, Long tid);

    List<PayChannel> findAllByTid(Long tid);
}
