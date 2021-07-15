package cn.bootx.salescenter.client.feign;

import cn.bootx.salescenter.client.OrderPreviewClient;
import cn.bootx.salescenter.dto.coupon.CouponDto;
import cn.bootx.salescenter.dto.order.GoodsActivityResult;
import cn.bootx.salescenter.dto.order.OrderPreviewResult;
import cn.bootx.salescenter.param.order.OrderCheckParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnMissingBean(OrderPreviewClient.class)
@RequiredArgsConstructor
public class OrderPreviewClientFeignImpl implements OrderPreviewClient {
    @Autowired(required = false)
    private OrderPreviewFeign orderPreviewFeign;

    @Override
    public List<GoodsActivityResult> findActivityByOrder(OrderCheckParam orderParam) {
        return orderPreviewFeign.findActivityByOrder(orderParam).getData();
    }

    @Override
    public List<CouponDto> findCouponByOrder(OrderCheckParam orderParam) {
        return orderPreviewFeign.findCouponByOrder(orderParam).getData();
    }

    @Override
    public OrderPreviewResult previewOrderPrice(OrderCheckParam orderParam) {
        return orderPreviewFeign.previewOrderPrice(orderParam).getData();
    }

    @Override
    public OrderPreviewResult previewOrderPriceNoCheck(OrderCheckParam orderParam) {
        return orderPreviewFeign.previewOrderPriceNoCheck(orderParam).getData();
    }

    @Override
    public OrderPreviewResult previewOrderPriceByAuto(OrderCheckParam orderParam) {
        return orderPreviewFeign.previewOrderPriceByAuto(orderParam).getData();
    }
}
