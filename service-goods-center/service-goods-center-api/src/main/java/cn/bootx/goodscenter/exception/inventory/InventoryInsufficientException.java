package cn.bootx.goodscenter.exception.inventory;


import cn.bootx.common.core.exception.BizException;

import static cn.bootx.goodscenter.code.GoodsCenterErrorCode.INVENTORY_INSUFFICIENT;

/**   
* 库存不足异常
* @author xxm  
* @date 2020/11/23 
*/
public class InventoryInsufficientException extends BizException {
    public InventoryInsufficientException() {
        super(INVENTORY_INSUFFICIENT, "库存不足异常");
    }
}
