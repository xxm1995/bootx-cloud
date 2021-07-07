package cn.bootx.paymentcenter.core.paymodel.point.manager;

import cn.bootx.paymentcenter.core.paymodel.point.dao.PointRecordRepository;
import cn.bootx.paymentcenter.core.paymodel.point.entity.PointRecord;
import cn.bootx.paymentcenter.core.paymodel.point.entity.QPointRecord;
import cn.bootx.starter.headerholder.HeaderHolder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 积分生成管理器
 * @author xxm
 * @date 2020/12/11
 */
@Repository
@RequiredArgsConstructor
public class PointRecordManager {
    private final PointRecordRepository pointRecordRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private final HeaderHolder headerHolder;

    /**
     * 查询用户负积分
     */
    public List<PointRecord> findNegativePoints(Long userId) {
        // select pg from PointGenerate pg
        // where pg.tenantId = ?1 and pg.userId=?2
        // and pg.currentPoints < 0 and pg.expireDate >= current_date order by pg.expireDate asc
        QPointRecord q = QPointRecord.pointRecord;
        return jpaQueryFactory.selectFrom(q)
                .where(
                        q.userId.eq(userId),
                        q.tid.eq(headerHolder.findTid()),
                        q.currentPoints.lt(0),
                        q.expireDate.goe(LocalDateTime.now())
                ).orderBy(q.expireDate.asc())
                .fetch();
    }

    /**
     * 获取用户可用积分
     */
    public Optional<Integer> findAvailablePoint(Long userId) {
        return pointRecordRepository.findAvailablePoint(userId,headerHolder.findTid(), LocalDateTime.now());
    }

    public List<PointRecord> findAvailablePointGenerateRecords(Long userId) {
//       "select pg from PointGenerate pg where
//                pg.tenantId = ?1 and pg.userId=?2 and pg.expireDate >= current_date and pg.currentPoints > 0
//                order by pg.expireDate, pg.createTime")
        QPointRecord q = QPointRecord.pointRecord;
        return jpaQueryFactory.selectFrom(q)
                .where(
                        q.userId.eq(userId),
                        q.tid.eq(headerHolder.findTid()),
                        q.expireDate.goe(LocalDateTime.now()),
                        q.currentPoints.gt(0)
                ).orderBy(q.expireDate.asc(),q.createTime.asc())
                .fetch();
    }

    public Optional<PointRecord> findById(Long id) {
        return pointRecordRepository.findByIdAndTid(id,headerHolder.findTid());
    }

    public Optional<PointRecord> findPointByBusinessId(Long userId, String businessId) {
//        @Query("select pg from PointGenerate pg where pg.tenantId = ?1 and pg.userId=?2 " +
//                "and pg.source = ?3 and pg.expireDate >= current_date and pg.currentPoints > 0")
        QPointRecord q = QPointRecord.pointRecord;
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(q)
                        .where(
                                q.userId.eq(userId),
                                q.businessId.eq(businessId),
                                q.tid.eq(headerHolder.findTid()),
                                q.expireDate.goe(LocalDateTime.now()),
                                q.currentPoints.gt(0)
                        ).fetchOne()
        );
    }

    public Optional<PointRecord> findByBusinessId(String businessId) {
        return pointRecordRepository.findByBusinessIdAndTid(businessId,headerHolder.findTid());
    }
}
