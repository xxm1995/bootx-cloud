package cn.bootx.ordercenter.core.order.dao;

import cn.bootx.ordercenter.core.order.entity.OrderWhole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderWholeRepository extends JpaRepository<OrderWhole,Long> {
    Optional<OrderWhole> findByIdAndTid(Long id, Long tid);
}
