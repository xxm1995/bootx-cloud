package cn.bootx.ordercenter.core.order.dao;

import cn.bootx.common.web.rest.param.PageParam;
import cn.bootx.ordercenter.code.OrderStatusCode;
import cn.bootx.ordercenter.core.order.entity.Order;
import cn.bootx.ordercenter.core.order.entity.QOrder;
import cn.bootx.ordercenter.core.order.entity.QOrderDetail;
import cn.bootx.starter.headerholder.HeaderHolder;
import cn.bootx.starter.jpa.utils.JpaUtils;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 订单
 * @author xxm
 * @date 2020/11/18
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderManager {
    private final OrderRepository orderRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private final HeaderHolder headerHolder;

    public Optional<Order> findById(Long id) {
        return orderRepository.findByIdAndTid(id,headerHolder.findTid());
    }

    public List<Order> findByUser(Long id) {
        return orderRepository.findAllByUserIdAndTid(id,headerHolder.findTid());
    }

    public Page<Order> page(PageParam page) {
        QOrder q = QOrder.order;
        JPAQuery<Order> query = jpaQueryFactory.selectFrom(q);
        return JpaUtils.queryPage(query,page);
    }

    /**
     * 获取订单中的sku列表
     */
    public List<Long> findOrderSkuIds(Long orderId){
        QOrderDetail q = QOrderDetail.orderDetail;
        return jpaQueryFactory
                .selectFrom(q)
                .select(q.skuId).distinct()
                .where(q.orderId.eq(orderId))
                .fetch();

    }

    /**
     * 获取超时订单的id集合
     */
    public List<Long> findPayTimeoutOrderIdsByType(LocalDateTime date, Integer type) {
        // select id from Order where state = ?1 and createTime < ?2 and businessId = ?3
        QOrder q = QOrder.order;
        return jpaQueryFactory
                .selectFrom(q)
                .select(q.id).distinct()
                .where(
                        q.createTime.before(date),
                        q.status.eq(OrderStatusCode.STATUS_NORMAL),
                        q.type.eq(type)
                ).fetch();

    }
}
