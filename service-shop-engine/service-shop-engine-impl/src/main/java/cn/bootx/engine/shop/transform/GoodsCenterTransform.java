package cn.bootx.engine.shop.transform;

import cn.bootx.engine.shop.core.cart.entity.ShopCartItem;
import cn.bootx.goodscenter.dto.sku.GoodsSkuDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 商品中心对象转换
 * @author xxm
 * @date 2021/2/17
 */
@Component
public class GoodsCenterTransform {

    public ShopCartItem transform(GoodsSkuDto goodsSku){
        ShopCartItem item = new ShopCartItem();
        BeanUtils.copyProperties(goodsSku,item);
        item.setNum(1)
                .setSelected(true)
                .setGoodsPrice(goodsSku.getPrice())
                .setGoodsTitle(goodsSku.getName())
                .setAddTime(LocalDateTime.now())
                .setCategoryId(goodsSku.getCid())
                .setSkuId(goodsSku.getId());
        return item;
    }
}
