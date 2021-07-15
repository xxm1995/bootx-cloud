package cn.bootx.engine.shop.core.cart.rule.impl;

import cn.bootx.common.web.exception.BizException;
import cn.bootx.engine.shop.core.cart.entity.ShopCartItem;
import cn.bootx.engine.shop.core.cart.entity.CartCheckParam;
import cn.bootx.engine.shop.core.cart.rule.CartOperateType;
import cn.bootx.engine.shop.core.cart.rule.func.CartCheck;
import cn.bootx.goodscenter.client.InventoryClient;
import cn.bootx.goodscenter.dto.sku.GoodsSkuDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

/**   
* 库存校验
* @author xxm  
* @date 2021/2/17 
*/
@Component
@RequiredArgsConstructor
public class InventoryCartCheckRule implements CartCheck {

    private final InventoryClient inventoryClient;
    private final List<CartOperateType> useTypes = Arrays.asList(CartOperateType.JOIN,CartOperateType.CHANGE_SPECIFICATION);

    @Override
    public boolean use(CartCheckParam param) {
        CartOperateType operateType = param.getType();
        return useTypes.contains(operateType);
    }

    @Override
    public void check(CartCheckParam param) {
        // 添加
        switch (param.getType()){
            case JOIN:{
                this.join(param);
                break;
            }
            case CHANGE_SPECIFICATION: {
                this.changeSpecification(param);
                break;
            }
            default:{
                throw new BizException("不支持的类型");
            }
        }
    }

    /**
     * 添加时检验
     */
    private void join(CartCheckParam param){
        GoodsSkuDto goodsSku = param.getGoodsSku();
        Integer available = inventoryClient.getAvailable(goodsSku.getId());

        LinkedHashMap<Long, ShopCartItem> cartItems = param.getShopCart().getCartItems();
        ShopCartItem shopCartItem = cartItems.get(goodsSku.getId());

        if (Objects.isNull(shopCartItem) && available>0){
            return;
        }
        if (shopCartItem.getNum()+1 > available){
            throw new BizException("库存不足");
        }
    }

    /**
     * 更改规格时检验
     */
    private void changeSpecification(CartCheckParam param) {
        GoodsSkuDto goodsSku = param.getNewGoodsSku();
        Integer available = inventoryClient.getAvailable(goodsSku.getId());

        if (available < 1){
            throw new BizException("库存不足");
        }
    }

}
