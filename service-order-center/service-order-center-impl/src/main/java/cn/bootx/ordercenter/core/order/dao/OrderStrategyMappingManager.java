package cn.bootx.ordercenter.core.order.dao;

import cn.bootx.ordercenter.core.order.entity.OrderStrategyMapping;
import cn.bootx.common.headerholder.HeaderHolder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* 订单策略映射
* @author xxm
* @date 2020/11/27
*/
@Repository
@RequiredArgsConstructor
public class OrderStrategyMappingManager {
    private final OrderStrategyMappingRepository mappingRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private final HeaderHolder headerHolder;

    public List<OrderStrategyMapping> findByOrder(Long orderId) {
        return mappingRepository.findAllByOrderIdAndTid(orderId,headerHolder.findTid());
    }
}
