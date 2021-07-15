package cn.bootx.ordercenter.core.order.dao;

import cn.bootx.ordercenter.core.order.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**   
* 订单明细
* @author xxm  
* @date 2020/11/18 
*/
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
    List<OrderDetail> findAllByOrderIdAndTid(Long orderId, Long tid);
}
