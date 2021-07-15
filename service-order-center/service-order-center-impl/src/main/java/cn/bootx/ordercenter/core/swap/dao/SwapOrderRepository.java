package cn.bootx.ordercenter.core.swap.dao;

import cn.bootx.ordercenter.core.swap.entity.SwapOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SwapOrderRepository extends JpaRepository<SwapOrder,Long> {
}
