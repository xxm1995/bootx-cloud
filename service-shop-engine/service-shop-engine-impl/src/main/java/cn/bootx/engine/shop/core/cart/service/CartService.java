package cn.bootx.engine.shop.core.cart.service;

import cn.bootx.common.core.exception.BizException;
import cn.bootx.engine.shop.core.cart.dao.CartManager;
import cn.bootx.engine.shop.core.cart.entity.ItemActivity;
import cn.bootx.engine.shop.core.cart.entity.ShopCart;
import cn.bootx.engine.shop.core.cart.entity.ShopCartItem;
import cn.bootx.engine.shop.transform.GoodsCenterTransform;
import cn.bootx.engine.shop.transform.SalesCenterTransform;
import cn.bootx.goods.client.GoodsSkuClient;
import cn.bootx.goods.dto.sku.GoodsSkuDto;
import cn.bootx.sales.client.OrderPreviewClient;
import cn.bootx.sales.dto.order.OrderDetailPreviewResult;
import cn.bootx.sales.dto.order.OrderPreviewResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 购物车服务
 * 更改购物车时计算出优惠金额并写缓存
 * todo 添加购物车需要进行检查
 * @author xxm
 * @date 2021/1/4
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {
    private final CartManager cartManager;

    private final OrderPreviewClient orderPreviewClient;
    private final GoodsSkuClient goodsSkuClient;

    private final CartCheckService cartCheckService;

    private final SalesCenterTransform salesCenterTransform;
    private final GoodsCenterTransform goodsCenterTransform;


    private final Long userId = 1L;


    /**
     * 添加一个商品到购物车中
     */
    public void add(Long skuId){
        ShopCart shopCart = cartManager.get(userId);
        Map<Long, ShopCartItem> map = shopCart.getCartItems();

        // 判断是否存在
        ShopCartItem shopCartItem = map.get(skuId);
        if (Objects.isNull(shopCartItem)){
            // 检查
            GoodsSkuDto goodsSku = goodsSkuClient.findById(skuId);
            cartCheckService.joinCheck(goodsSku,shopCart);
            ShopCartItem item = goodsCenterTransform.transform(goodsSku);

            map.put(skuId,item);
        } else {
            shopCartItem.setNum(shopCartItem.getNum()+1)
                    .setUpdateTime(LocalDateTime.now());
        }
        this.saveAndUpdate(shopCart);
    }

    /**
     * 删除购物车中的一个物品
     */
    public void remove(Long skuId){
        ShopCart shopCart = cartManager.get(userId);
        Map<Long, ShopCartItem> map = shopCart.getCartItems();

        Optional.ofNullable(map.get(skuId)).orElseThrow(() ->  new BizException("不存在"));
        map.remove(skuId);

        this.saveAndUpdate(shopCart);
    }

    /**
     * 更改数量
     */
    public void changeCount(Long skuId,int num){
        ShopCart shopCart = cartManager.get(userId);
        Map<Long, ShopCartItem> map = shopCart.getCartItems();

        ShopCartItem shopCartItem = Optional.ofNullable(map.get(skuId)).orElseThrow(() ->  new BizException("不存在"));
        shopCartItem.setNum(num);

        this.saveAndUpdate(shopCart);
    }

    /**
     * 更改规格
     */
    public void changeSpecifications(Long oldSkuId,Long newSkuId){
        ShopCart shopCart = cartManager.get(userId);
        Map<Long, ShopCartItem> map = shopCart.getCartItems();

        Optional.ofNullable(map.get(oldSkuId)).orElseThrow(() ->  new BizException("不存在"));
        if (map.containsKey(newSkuId)){
            throw new BizException("该规格已经存在");
        }
        // 检查
        GoodsSkuDto goodsSku = goodsSkuClient.findById(newSkuId);
        cartCheckService.joinCheck(goodsSku,shopCart);

        ShopCartItem newItem = goodsCenterTransform.transform(goodsSku);

        map.remove(oldSkuId);
        map.put(newSkuId,newItem);

        this.saveAndUpdate(shopCart);
    }

    /**
     * 选择优惠
     */
    public void selectActivity(Long skuId,Long activityId){
        ShopCart shopCart = cartManager.get(userId);
        Map<Long, ShopCartItem> map = shopCart.getCartItems();

        ShopCartItem item = Optional.ofNullable(map.get(skuId)).orElseThrow(() ->  new BizException("不存在"));
        List<ItemActivity> activities = Collections.singletonList(new ItemActivity()
                .setActId(activityId)
                .setName("测试用优惠"));
        item.setActivities(activities);
        this.saveAndUpdate(shopCart);
    }

    /**
     * 选中或取消选中 1 s 2 un
     */
    public void select(Long skuId,int type){
        ShopCart shopCart = cartManager.get(userId);
        Map<Long, ShopCartItem> map = shopCart.getCartItems();

        ShopCartItem oldItem = Optional.ofNullable(map.get(skuId)).orElseThrow(() ->  new BizException("不存在"));
        oldItem.setSelected(type==1);

        this.saveAndUpdate(shopCart);
    }


    /**
     * 获取完整购物车
     */
    public ShopCart getWhole(){
        ShopCart shopCart = cartManager.get(userId);
        return shopCart;
    }


    /**
     * 获取选中的商品信息
     */
    public List<ShopCartItem> getSelected(){
        ShopCart shopCart = cartManager.get(userId);
        Collection<ShopCartItem> values = shopCart.getCartItems().values();
        return values.stream()
                .filter(ShopCartItem::isSelected)
                .collect(Collectors.toList());
    }

    /**
     * 清除购物车中选择的商品
     */
    public void clear(List<ShopCartItem> selected) {
        ShopCart shopCart = cartManager.get(userId);
        Map<Long, ShopCartItem> cartItems = shopCart.getCartItems();
        selected.stream().map(ShopCartItem::getSkuId)
                .forEach(cartItems::remove);
        this.saveAndUpdate(shopCart);
    }

    /**
     * 清除选择的
     */
    public void clearByIds(List<Long> skuIds) {
        ShopCart shopCart = cartManager.get(userId);
        Map<Long, ShopCartItem> cartItems = shopCart.getCartItems();
        skuIds.forEach(cartItems::remove);
        this.saveAndUpdate(shopCart);
    }

    /**
     * 保存or更新
     */
    public void saveAndUpdate(ShopCart shopCart){
        // 费用计算
        OrderPreviewResult orderCheckDto = orderPreviewClient.previewOrderPriceNoCheck(salesCenterTransform.toOrderCheck(shopCart));
        Map<Long, OrderDetailPreviewResult> detailCheckMap = orderCheckDto.getOrderDetails().stream()
                .collect(Collectors.toMap(OrderDetailPreviewResult::getSkuId, o -> o));

        // 金额回写购物车
        shopCart.getCartItems().forEach(
                (skuId, shopCartItem) -> {
                    OrderDetailPreviewResult checkDto = detailCheckMap.get(skuId);
                    // 有优惠信息的显示优惠价,没有的显示原价
                    if (Objects.nonNull(checkDto)){
                        shopCartItem.setPayAmount(checkDto.getPayAmount())
                                .setTotalAmount(checkDto.getTotalAmount())
                                .setPayChange(checkDto.getPayChange());
                    } else {
                        BigDecimal totalAmount = shopCartItem.getGoodsPrice().multiply(BigDecimal.valueOf(shopCartItem.getNum()));
                        shopCartItem.setPayChange(null)
                                .setTotalAmount(totalAmount)
                                .setPayAmount(totalAmount);
                    }
                }
        );

        shopCart.setPayAmount(orderCheckDto.getPayAmount())
                .setTotalAmount(orderCheckDto.getTotalAmount());
        cartManager.save(shopCart);
    }

}
