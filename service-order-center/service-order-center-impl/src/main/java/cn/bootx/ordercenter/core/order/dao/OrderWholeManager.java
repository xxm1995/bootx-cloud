package cn.bootx.ordercenter.core.order.dao;

import cn.bootx.ordercenter.core.order.entity.OrderWhole;
import cn.bootx.common.headerholder.HeaderHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**   
* 订单整体信息
* @author xxm  
* @date 2021/3/14 
*/
@Repository
@RequiredArgsConstructor
public class OrderWholeManager {
    private final OrderWholeRepository orderWholeRepository;

    private final HeaderHolder headerHolder;

    public Optional<OrderWhole> findById(Long id) {
        return orderWholeRepository.findByIdAndTid(id,headerHolder.findTid());
    }
}
