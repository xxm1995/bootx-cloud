package cn.bootx.goodscenter.exception.sku;


import cn.bootx.common.core.exception.BizException;

import static cn.bootx.goodscenter.code.GoodsCenterErrorCode.GOODS_SKU_NOT_ON;

/**
* 商品 SKU 尚未开售异常
* @author xxm
* @date 2020/11/24
*/
public class SkuNotOnException extends BizException {
    public SkuNotOnException() {
        super(GOODS_SKU_NOT_ON, "商品 SKU 尚未开售异常");
    }
}
