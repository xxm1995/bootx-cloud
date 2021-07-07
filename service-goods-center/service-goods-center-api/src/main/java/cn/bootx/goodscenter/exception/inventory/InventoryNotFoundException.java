package cn.bootx.goodscenter.exception.inventory;


import cn.bootx.common.web.exception.BizException;

import static cn.bootx.goodscenter.code.GoodsCenterErrorCode.INVENTORY_NOT_FOUND;


/**   
* 库存未找到异常
* @author xxm  
* @date 2020/11/22 
*/
public class InventoryNotFoundException extends BizException {
    public InventoryNotFoundException() {
        super(INVENTORY_NOT_FOUND, "找不到库存");
    }
}
