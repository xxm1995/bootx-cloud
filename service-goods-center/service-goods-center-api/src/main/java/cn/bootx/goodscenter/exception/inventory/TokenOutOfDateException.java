package cn.bootx.goodscenter.exception.inventory;

import cn.bootx.common.web.exception.BizException;

import static cn.bootx.goodscenter.code.GoodsCenterErrorCode.TOKEN_OUT_OF_DADE;

/**
* 库存Token已过期
* @author xxm
* @date 2020/11/23
*/
public class TokenOutOfDateException extends BizException {
    public TokenOutOfDateException() {
        super(TOKEN_OUT_OF_DADE, "库存Token已过期");
    }
}