package cn.bootx.paymentcenter.core.payment.dao;

import cn.bootx.common.web.rest.param.PageParam;
import cn.bootx.paymentcenter.code.pay.PayStatusCode;
import cn.bootx.paymentcenter.core.payment.entity.Payment;
import cn.bootx.paymentcenter.core.payment.entity.QPayment;
import cn.bootx.paymentcenter.param.payment.PaymentQuery;
import cn.bootx.starter.headerholder.HeaderHolder;
import cn.bootx.starter.jpa.utils.JpaUtils;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PaymentManager {
    private final PaymentRepository paymentRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private final HeaderHolder headerHolder;

    /**
     * 获取payment记录
     */
    public Optional<Payment> findById(Long id) {
        return paymentRepository.findByIdAndTid(id,headerHolder.findTid());
    }

    /**
     * 更新payment的支付状态
     */
    @Transactional(rollbackFor = Exception.class)
    public int updatePayStatus(Long id, int status, String errorCode) {
        return paymentRepository.updatePayStatusAndTid(id, status, errorCode,headerHolder.findTid());
    }

    /**
     * 按业务ID顺序按创建时间Desc查找非取消的支付单
     */
    public List<Payment> findByBusinessIdNoCancelDesc(String businessId) {
        // "FROM Payment WHERE businessId = ?1 AND payStatus  ?2 order by createTime desc"
        QPayment q = QPayment.payment;
        return jpaQueryFactory.selectFrom(q)
                .where(
                        q.businessId.eq(businessId),
                        q.payStatus.notIn(PayStatusCode.TRADE_CANCEL),
                        q.tid.eq(headerHolder.findTid())
                ).orderBy(q.createTime.desc())
                .fetch();
    }

    /**
     * 按业务ID顺序按创建时间Desc查找的支付单
     */
    public List<Payment> findByBusinessIdDesc(String businessId) {
        // "FROM Payment WHERE businessId = ?1  order by createTime desc"
        QPayment q = QPayment.payment;
        return jpaQueryFactory.selectFrom(q)
                .where(
                        q.businessId.eq(businessId),
                        q.tid.eq(headerHolder.findTid())
                ).orderBy(q.createTime.desc())
                .fetch();
    }

    /**
     * 查找超时付款订单
     */
    public List<Long> findPayTimeoutIds(LocalDateTime dateTime) {
        QPayment q = QPayment.payment;
        return jpaQueryFactory
                .selectFrom(q)
                .select(q.id).distinct()
                .where(
                        q.createTime.before(dateTime),
                        q.payStatus.eq(PayStatusCode.TRADE_PROGRESS)
                ).fetch();
    }

    /**
     * 根据用户查询
     */
    public List<Payment> findByUserId(Long userId){
        return paymentRepository.findByUserIdAndTid(userId,headerHolder.findTid());
    }

    public Page<Payment> page(PageParam pageParam, PaymentQuery param) {
        QPayment q = QPayment.payment;
        JPAQuery<Payment> query = jpaQueryFactory.selectFrom(q);

        return JpaUtils.queryPage(query,pageParam);
    }
}
