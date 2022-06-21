package cn.bootx.paymentcenter.core.billing.service;

import cn.bootx.paymentcenter.core.billing.dao.OrderItemBillingManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**   
* 订单明细账单服务
* @author xxm  
* @date 2021/2/25 
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderItemBillingService {
    private final OrderItemBillingManager orderItemBillingManager;
}
