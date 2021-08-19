package cn.bootx.paymentcenter.core.billing.dao;

import cn.bootx.paymentcenter.core.billing.entity.OrderBilling;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderBillingRepository extends JpaRepository<OrderBilling, Long>, JpaSpecificationExecutor<OrderBilling> {
}
