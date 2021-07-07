package cn.bootx.paymentcenter.core.paymodel.point.manager;

import cn.bootx.paymentcenter.code.pay.PayStatusCode;
import cn.bootx.paymentcenter.core.paymodel.point.dao.PointPaymentRepository;
import cn.bootx.paymentcenter.core.paymodel.point.entity.PointPayment;
import cn.bootx.paymentcenter.core.paymodel.point.entity.QPointPayment;
import cn.bootx.starter.headerholder.HeaderHolder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
* 积分支付
* @author xxm
* @date 2020/12/11
*/
@Repository
@RequiredArgsConstructor
public class PointPaymentManager {
    private final PointPaymentRepository pointPaymentRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private final HeaderHolder headerHolder;

    public List<PointPayment> findByBusinessId(Long userId, String businessId) {
        //@Query("select pp from PointPayment pp where " +
        //            "pp.tenantId = ?1 and pp.userId = ?2 and pp.source = ?3 and pp.payStatus= ?4 " +
        //            "order by pp.pointGenerateId desc ")
        QPointPayment q = QPointPayment.pointPayment;
        return jpaQueryFactory.selectFrom(q)
                .where(
                        q.userId.eq(userId),
                        q.businessId.eq(businessId),
                        q.tid.eq(headerHolder.findTid()),
                        q.payStatus.eq(PayStatusCode.TRADE_SUCCESS)
                ).orderBy(q.pointGenerateId.desc())
        .fetch();
    }

    /**
     * 积分支付payment
     */
    public List<PointPayment> findByPaymentId(Long paymentId) {
        return pointPaymentRepository.findAllByPaymentIdAndTid(paymentId,headerHolder.findTid());
    }
}
