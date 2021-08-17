package cn.bootx.paymentcenter.core.billing.dao;

import cn.bootx.common.headerholder.HeaderHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderBillingManager {
    private final OrderBillingRepository orderBillingRepository;
    private final HeaderHolder headerHolder;

}
