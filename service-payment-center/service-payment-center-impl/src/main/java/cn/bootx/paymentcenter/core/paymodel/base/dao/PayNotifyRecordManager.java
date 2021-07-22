package cn.bootx.paymentcenter.core.paymodel.base.dao;

import cn.bootx.common.web.rest.param.PageParam;
import cn.bootx.paymentcenter.core.paymodel.base.entity.PayNotifyRecord;
import cn.bootx.paymentcenter.core.paymodel.base.entity.QPayNotifyRecord;
import cn.bootx.starter.headerholder.HeaderHolder;
import cn.bootx.starter.jpa.utils.JpaUtils;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 支付记录
 * @author xxm
 * @date 2021/6/22
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class PayNotifyRecordManager {
    private final PayNotifyRecordRepository repository;
    private final HeaderHolder headerHolder;
    private final JPAQueryFactory jpaQueryFactory;

    public Page<PayNotifyRecord> page(PageParam pageParam){
        QPayNotifyRecord q = QPayNotifyRecord.payNotifyRecord;
        JPAQuery<PayNotifyRecord> query = jpaQueryFactory.selectFrom(q);


        query.where(q.tid.eq(headerHolder.findTid()));
        return JpaUtils.queryPage(query,pageParam);
    }

    public Optional<PayNotifyRecord> findById(Long id){
        return repository.findByIdAndTid(id,headerHolder.findTid());
    }
}
