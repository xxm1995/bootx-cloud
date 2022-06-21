package cn.bootx.paymentcenter.core.billing.dao;

import cn.bootx.paymentcenter.core.billing.entity.OrderBilling;
import cn.bootx.starter.headerholder.HeaderHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderBillingManager {
    private final OrderBillingRepository orderBillingRepository;
    private final HeaderHolder headerHolder;

}
