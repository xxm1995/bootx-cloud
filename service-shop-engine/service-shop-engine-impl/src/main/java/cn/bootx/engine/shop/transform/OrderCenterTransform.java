package cn.bootx.engine.shop.transform;

import cn.bootx.engine.shop.core.cart.entity.ItemActivity;
import cn.bootx.engine.shop.core.cart.entity.ShopCartItem;
import cn.bootx.goods.client.GoodsSkuClient;
import cn.bootx.goods.dto.sku.GoodsSkuDto;
import cn.bootx.order.param.order.OrderDetailParam;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 订单工厂
 * @author xxm
 * @date 2021/3/9
 */
@Component
@RequiredArgsConstructor
public class OrderCenterTransform {
    private final GoodsSkuClient goodsSkuClient;

    /**
     * 商城购物车明细转商品明细参数
     */
    public List<OrderDetailParam> cartItem2OrderDetail(List<ShopCartItem> shopCartItems){
        List<Long> skuIds = shopCartItems.stream()
                .map(ShopCartItem::getSkuId)
                .collect(Collectors.toList());
        Map<Long, GoodsSkuDto> skuMap = goodsSkuClient.findBySkuIds(skuIds).stream()
                .collect(Collectors.toMap(GoodsSkuDto::getId, o -> o));
        return shopCartItems.stream()
                .map(item -> this.cartItem2OrderDetail(item,skuMap.get(item.getSkuId())))
                .collect(Collectors.toList());
    }

    /**
     * 商城购物车明细转商品明细参数
     */
    public OrderDetailParam cartItem2OrderDetail(ShopCartItem shopCartItem,GoodsSkuDto goodsSkus){
        OrderDetailParam orderDetailParam = new OrderDetailParam();
        BeanUtil.copyProperties(shopCartItem,orderDetailParam);

        // 价格
        orderDetailParam.setGoodsPrice(goodsSkus.getPrice())
                .setGoodsTitle(goodsSkus.getName());
        // 优惠信息
        if (CollUtil.isNotEmpty(shopCartItem.getActivities())){
            List<Long> actIds = shopCartItem.getActivities().stream()
                    .map(ItemActivity::getActId)
                    .collect(Collectors.toList());
            orderDetailParam.setActivityIds(actIds);
        }

        return orderDetailParam;
    }
}
