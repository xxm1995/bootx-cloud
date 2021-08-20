package cn.bootx.engine.shop.transform;

import cn.bootx.engine.shop.core.cart.entity.ItemActivity;
import cn.bootx.engine.shop.core.cart.entity.ShopCart;
import cn.bootx.engine.shop.core.cart.entity.ShopCartItem;
import cn.bootx.sales.param.order.OrderCheckParam;
import cn.bootx.sales.param.order.OrderDetailCheckParam;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* 销售中心对象转换
* @author xxm
* @date 2021/2/17
*/
@Component
public class SalesCenterTransform {

    /**
     * 转换为销售中心计费参数对象
     */
    public OrderCheckParam toOrderCheck(ShopCart shopCart){
        OrderCheckParam orderParam = new OrderCheckParam();
        BeanUtils.copyProperties(shopCart,orderParam);
        List<OrderDetailCheckParam> details = shopCart.getCartItems().values().stream()
                // 只计算选中的购物车明细
                .filter(ShopCartItem::isSelected)
                .map(this::toOrderDetailCheck)
                .collect(Collectors.toList());
        return orderParam.setDetails(details);
    }

    /**
     * 转换 购物车明细
     */
    public OrderDetailCheckParam toOrderDetailCheck(ShopCartItem shopCartItem){
        OrderDetailCheckParam detailParam = new OrderDetailCheckParam();
        BeanUtils.copyProperties(shopCartItem,detailParam);

        List<ItemActivity> itemActivities = Optional.ofNullable(shopCartItem.getActivities())
                .orElse(Collections.emptyList());
        List<Long> activityIds = itemActivities.stream()
                .map(ItemActivity::getActId)
                .collect(Collectors.toList());
        detailParam.setGoodsPrice(shopCartItem.getGoodsPrice())
                .setGoodsTitle(shopCartItem.getName())
                .setActivityIds(activityIds);
        return detailParam;
    }
}
