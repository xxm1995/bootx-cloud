package cn.bootx.paymentcenter.core.billing.dao;

import cn.bootx.paymentcenter.core.billing.entity.OrderItemBilling;
import cn.bootx.paymentcenter.param.billing.BillingItemQueryParam;
import cn.hutool.core.collection.CollectionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 订单明细账单
 * @author xxm
 * @date 2020/12/11
 */
@Service
@RequiredArgsConstructor
public class OrderItemBillingManager {

    private final OrderItemBillingRepository orderItemBillingRepository;

}
