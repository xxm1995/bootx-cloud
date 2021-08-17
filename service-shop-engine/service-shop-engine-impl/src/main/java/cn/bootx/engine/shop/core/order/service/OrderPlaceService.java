package cn.bootx.engine.shop.core.order.service;

import cn.bootx.common.util.BigDecimalUtil;
import cn.bootx.engine.shop.core.cart.entity.ShopCartItem;
import cn.bootx.engine.shop.core.cart.service.CartService;
import cn.bootx.engine.shop.core.order.dao.OrderCacheManager;
import cn.bootx.engine.shop.param.sell.NowPlaceOrderParam;
import cn.bootx.engine.shop.param.sell.PlaceOrderParam;
import cn.bootx.engine.shop.transform.OrderCenterTransform;
import cn.bootx.goodscenter.client.GoodsSkuClient;
import cn.bootx.goodscenter.client.InventoryClient;
import cn.bootx.goodscenter.dto.inventory.LockInventoryDto;
import cn.bootx.goodscenter.dto.sku.GoodsSkuDto;
import cn.bootx.goodscenter.exception.inventory.InventoryInsufficientException;
import cn.bootx.ordercenter.client.OrderOperateClient;
import cn.bootx.ordercenter.dto.order.OrderDto;
import cn.bootx.ordercenter.param.order.OrderDetailParam;
import cn.bootx.ordercenter.param.order.OrderParam;
import cn.bootx.ordercenter.param.order.OrderWholeParam;
import cn.bootx.common.auth.utils.SecurityUtils;
import cn.bootx.common.headerholder.HeaderHolder;
import cn.hutool.core.bean.BeanUtil;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**   
* 下单服务
* @author xxm  
* @date 2020/12/11 
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderPlaceService {
    private final OrderCacheManager orderCacheManager;

    private final OrderCenterTransform orderCenterTransform;

    private final OrderOperateClient orderOperateClient;
    private final InventoryClient inventoryClient;
    private final GoodsSkuClient goodsSkuClient;

    private final CartService cartService;
    private final HeaderHolder headerHolder;
    private final SecurityUtils securityUtils;
    private final Long userId = 1001L;

    /**
     * 通过购物车下单
     */
    @GlobalTransactional(rollbackFor = Exception.class)
    public OrderDto createOrderByCart(PlaceOrderParam param){
        // 1获取购物车中选定的商品
        List<ShopCartItem> selected = cartService.getSelected();

        // 转换成商品明细
        List<OrderDetailParam> orderDetailParams = orderCenterTransform.cartItem2OrderDetail(selected);

        OrderDto order = this.createOrder(param,orderDetailParams);
        // 清除购物车
        cartService.clear(selected);

        return order;
    }

    /**
     * 立即购买
     */
    public OrderDto buyNow(NowPlaceOrderParam param){

        GoodsSkuDto s1 = goodsSkuClient.findById(param.getSkuId());
        GoodsSkuDto s2 = goodsSkuClient.findById(2L);
        OrderDetailParam od1 = new OrderDetailParam();
        OrderDetailParam od2 = new OrderDetailParam();
        BeanUtil.copyProperties(s1,od1);
        BeanUtil.copyProperties(s2,od1);
        od1.setNum(param.getCount())
                .setGoodsPrice(s1.getPrice())
                .setGoodsId(s1.getGoodsId())
                .setSkuId(s1.getId())
                .setCategoryId(s1.getId())
                .setGoodsTitle(s1.getName());

        od2.setNum(param.getCount())
                .setGoodsPrice(s2.getPrice())
                .setGoodsId(s2.getGoodsId())
                .setSkuId(s2.getId())
                .setCategoryId(s2.getId())
                .setGoodsTitle(s2.getName());
        List<OrderDetailParam> orderDetailParams = Arrays.asList(od1,od2);
        return this.createOrder(param,orderDetailParams);
    }

    /**
     * 下单
     */
    public OrderDto createOrder(PlaceOrderParam param, List<OrderDetailParam> orderDetailParams){

        // 构建下单参数
        OrderParam orderParam = this.buildOrderParam(param, orderDetailParams);

        // 校验库存和锁checkInventory定库存
        this.checkInventory(orderDetailParams);
        Map<Long, LockInventoryDto> inventoryMap = this.lockInventory(orderDetailParams);
        OrderWholeParam orderWholeParam = new OrderWholeParam()
                .setOrderParam(orderParam);

        // 下单
        OrderDto order = orderOperateClient.placeOrder(orderWholeParam);

        // 预占库存token缓存
        inventoryMap.forEach((skuId, lockInventory) -> {
            //将token写入redis
            orderCacheManager.saveInventoryLockToken(order.getId(), skuId, lockInventory.getToken());
        });
        // 保存订单超时时间
        orderCacheManager.storeByOrderId(order.getId());
        return order;
    }

    /**
     * 检查库存
     */
    private void checkInventory(List<OrderDetailParam> detailParams) {

        for (OrderDetailParam orderDetail : detailParams) {
            int inventory = Optional.ofNullable(inventoryClient.getAvailable(orderDetail.getSkuId())).orElse(0);

            if (inventory < orderDetail.getNum()) {
                throw new InventoryInsufficientException();
            }
        }
    }

    /**
     * 锁定库存
     */
    private Map<Long,LockInventoryDto> lockInventory(List<OrderDetailParam> orderDetailParams) {
        Map<Long,LockInventoryDto> map = new LinkedHashMap<>();
        for (OrderDetailParam detailParam : orderDetailParams) {
            LockInventoryDto inventory = inventoryClient.lockInventory(detailParam.getSkuId(), detailParam.getNum());
            map.put(detailParam.getSkuId(),inventory);
        }
        return map;
    }

    /**
     * 构建订单参数
     *
     */
    private OrderParam buildOrderParam(PlaceOrderParam param, List<OrderDetailParam> orderDetailParams) {

        // 订单
        OrderParam orderParam = new OrderParam();
//        Optional.ofNullable(authClient.getUserInfoBySession())
//                .orElseThrow(() -> new BizException("未登录"));

        BigDecimal totalAmount = orderDetailParams.stream()
                .map(detail -> BigDecimalUtil.multiply(detail.getGoodsPrice(), BigDecimal.valueOf(detail.getNum())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        orderParam.setUserId(userId);
        orderParam.setChannelId(headerHolder.getChannelId());
        orderParam.setType(1);
        orderParam.setTotalAmount(totalAmount);

        orderParam.setDetails(orderDetailParams);
        return orderParam;
    }
}
