package cn.bootx.paymentcenter.core.paymodel.base.dao;

import cn.bootx.paymentcenter.core.paymodel.base.entity.PayNotifyRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PayNotifyRecordRepository extends JpaRepository<PayNotifyRecord,Long> {
    Optional<PayNotifyRecord> findByIdAndTid(Long id, Long tid);
}
