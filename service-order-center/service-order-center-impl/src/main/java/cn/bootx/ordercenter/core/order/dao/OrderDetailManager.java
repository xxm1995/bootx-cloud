package cn.bootx.ordercenter.core.order.dao;

import cn.bootx.ordercenter.core.order.entity.OrderDetail;
import cn.bootx.common.headerholder.HeaderHolder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 订单明细
* @author xxm
* @date 2020/11/18
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderDetailManager {
    private final OrderDetailRepository orderDetailRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private final HeaderHolder headerHolder;

    public List<OrderDetail> findByOrder(Long orderId){
        return orderDetailRepository.findAllByOrderIdAndTid(orderId,headerHolder.findTid());
    }
}
