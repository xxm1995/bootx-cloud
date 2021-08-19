package cn.bootx.paymentcenter.core.paymodel.point.dao;


import cn.bootx.paymentcenter.core.paymodel.point.entity.PointPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PointPaymentRepository extends JpaRepository<PointPayment, Long>, JpaSpecificationExecutor<PointPayment> {


    List<PointPayment> findAllByPaymentIdAndTid(long paymentId, Long tid);
}
