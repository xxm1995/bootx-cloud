package cn.bootx.engine.shop.core.cart.entity;

import cn.bootx.engine.shop.core.cart.rule.CartOperateType;
import cn.bootx.goods.dto.sku.GoodsSkuDto;
import lombok.Data;
import lombok.experimental.Accessors;

/**
* 购物车检查参数
* @author xxm
* @date 2021/2/17
*/
@Data
@Accessors(chain = true)
public class CartCheckParam {

    /** 要操作的sku信息 */
    private GoodsSkuDto goodsSku;

    /** 购物车 */
    private ShopCart shopCart;

    /** 操作类型 */
    private CartOperateType type;

    /** 操作数量 */
    private Integer num;

    /** 要操作的sku信息 */
    private GoodsSkuDto newGoodsSku;
}
