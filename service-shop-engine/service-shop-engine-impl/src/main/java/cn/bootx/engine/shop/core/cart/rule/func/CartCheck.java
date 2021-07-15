package cn.bootx.engine.shop.core.cart.rule.func;

import cn.bootx.engine.shop.core.cart.entity.CartCheckParam;

/**
* 购物车检查
* @author xxm
* @date 2021/2/17
*/
public interface CartCheck {

    /**
     * 是否支持
     */
    boolean use(CartCheckParam param);

    /**
     * 验证
     */
    void check(CartCheckParam param);
}
