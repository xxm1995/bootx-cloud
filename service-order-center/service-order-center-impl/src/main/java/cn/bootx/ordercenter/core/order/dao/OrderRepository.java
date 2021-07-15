package cn.bootx.ordercenter.core.order.dao;

import cn.bootx.ordercenter.core.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {
    Optional<Order> findByIdAndTid(Long id, Long tid);

    List<Order> findAllByUserIdAndTid(Long id, Long tid);
}
