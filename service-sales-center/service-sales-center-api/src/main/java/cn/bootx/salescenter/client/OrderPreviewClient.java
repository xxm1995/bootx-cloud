package cn.bootx.salescenter.client;

import cn.bootx.salescenter.dto.coupon.CouponDto;
import cn.bootx.salescenter.dto.order.GoodsActivityResult;
import cn.bootx.salescenter.dto.order.OrderPreviewResult;
import cn.bootx.salescenter.param.order.OrderCheckParam;

import java.util.List;

/**
* 订单预览
* @author xxm
* @date 2020/11/26 
*/
public interface OrderPreviewClient {
    /**
     * 传入订单,返回所有可用的优惠go
     */
    List<GoodsActivityResult> findActivityByOrder(OrderCheckParam orderParam);

    /**
     * 获取订单可用的优惠券
     */
    List<CouponDto> findCouponByOrder(OrderCheckParam orderParam);

    /**
     * 预览价格(手动)
     */
    OrderPreviewResult previewOrderPrice(OrderCheckParam orderParam);

    /**
     * 预览价格不检查合法性(手动)
     */
    OrderPreviewResult previewOrderPriceNoCheck(OrderCheckParam orderParam);

    /**
     * 自动匹配策略并计算价格
     */
    OrderPreviewResult previewOrderPriceByAuto(OrderCheckParam orderParam);
}
