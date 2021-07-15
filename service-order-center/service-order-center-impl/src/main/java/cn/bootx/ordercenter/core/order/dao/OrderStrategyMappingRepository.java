package cn.bootx.ordercenter.core.order.dao;

import cn.bootx.ordercenter.core.order.entity.OrderStrategyMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderStrategyMappingRepository extends JpaRepository<OrderStrategyMapping,Long> {

    List<OrderStrategyMapping> findAllByOrderIdAndTid(Long orderId, Long tid);
}
