package cn.bootx.goods.exception.goods;

import cn.bootx.common.core.exception.BizException;

import static cn.bootx.goods.code.GoodsCenterErrorCode.GOODS_NOT_EXISTED;

/**
*
* @author xxm
* @date 2020/12/10
*/
public class GoodsNotOnException extends BizException {
    public GoodsNotOnException() {
        super(GOODS_NOT_EXISTED, "商品不存在");
    }
}
