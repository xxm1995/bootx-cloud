package cn.bootx.goodscenter.exception.goods;

import cn.bootx.common.web.exception.BizException;

import static cn.bootx.goodscenter.code.GoodsCenterErrorCode.GOODS_NOT_EXISTED;

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
