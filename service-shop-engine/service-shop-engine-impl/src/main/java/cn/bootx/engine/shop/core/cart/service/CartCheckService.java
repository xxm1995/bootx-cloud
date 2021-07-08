package cn.bootx.engine.shop.core.cart.service;

import cn.bootx.engine.shop.core.cart.entity.ShopCart;
import cn.bootx.engine.shop.core.cart.entity.CartCheckParam;
import cn.bootx.engine.shop.core.cart.rule.CartOperateType;
import cn.bootx.engine.shop.core.cart.rule.func.CartCheck;
import cn.bootx.goodscenter.dto.sku.GoodsSkuDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
*
* @author xxm  
* @date 2021/2/17 
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class CartCheckService {
    private final List<CartCheck> cartChecks;

    /**
     * 添加时验证
     */
    public void joinCheck(GoodsSkuDto goodsSku,ShopCart shopCart){
        CartCheckParam checkParam = new CartCheckParam()
                .setGoodsSku(goodsSku)
                .setShopCart(shopCart)
                .setType(CartOperateType.JOIN);
        for (CartCheck cartCheck : cartChecks) {
            if (cartCheck.use(checkParam)){
                cartCheck.check(checkParam);
            }
        }
    }


    /**
     * 列表整体校验
     */
    public void wholeCheck(ShopCart shopCart){
        
    }


}
