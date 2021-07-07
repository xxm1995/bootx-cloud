package cn.bootx.paymentcenter.core.paymodel.point.dao;

import cn.bootx.paymentcenter.core.paymodel.point.entity.PointLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface PointLogRepository extends JpaRepository<PointLog, Long>, JpaSpecificationExecutor<PointLog> {
}
