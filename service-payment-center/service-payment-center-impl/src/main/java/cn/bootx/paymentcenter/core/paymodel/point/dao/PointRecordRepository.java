package cn.bootx.paymentcenter.core.paymodel.point.dao;

import cn.bootx.paymentcenter.core.paymodel.point.entity.PointRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PointRecordRepository extends JpaRepository<PointRecord, Long> {

    @Query("select nullif(sum(currentPoints), 0) from PointRecord where userId=?1 and tid = ?2 and expireDate >= ?3")
    Optional<Integer> findAvailablePoint(Long userId, Long tid, LocalDateTime currentDate);

    Optional<PointRecord> findByIdAndTid(Long id, Long tid);

    Optional<PointRecord> findByBusinessIdAndTid(String businessId, Long tid);
}
