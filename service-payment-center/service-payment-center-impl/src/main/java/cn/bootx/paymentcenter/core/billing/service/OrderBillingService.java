package cn.bootx.paymentcenter.core.billing.service;

import cn.bootx.paymentcenter.core.billing.dao.OrderBillingManager;
import cn.bootx.paymentcenter.core.billing.dao.OrderItemBillingManager;
import cn.bootx.paymentcenter.core.payment.dao.PaymentManager;
import cn.bootx.paymentcenter.core.payment.factory.PaymentFactory;
import cn.bootx.common.snowflake.SnowFlakeId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * 账单服务
 * @author xxm
 * @date 2021/2/25
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderBillingService {

    private final OrderBillingManager orderBillingManager;
    private final OrderItemBillingManager orderItemBillingManager;
    private final PaymentFactory paymentFactory;
    private final PaymentManager paymentManager;
    private final SnowFlakeId snowFlakeId;


    /**
     * billing 的拆分操作
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void handleBilling(Long paymentId) {
    }
}
