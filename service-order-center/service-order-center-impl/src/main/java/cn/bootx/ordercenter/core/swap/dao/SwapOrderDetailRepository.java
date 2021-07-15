package cn.bootx.ordercenter.core.swap.dao;


import cn.bootx.ordercenter.core.swap.entity.SwapOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SwapOrderDetailRepository extends JpaRepository<SwapOrderDetail,Long> {
}
