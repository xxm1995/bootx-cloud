package cn.bootx.paymentcenter.core.billing.dao;

import cn.bootx.paymentcenter.core.billing.entity.OrderItemBilling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemBillingRepository extends JpaRepository<OrderItemBilling, Long>, JpaSpecificationExecutor<OrderItemBilling> {

}
