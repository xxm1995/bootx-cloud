package cn.bootx.ordercenter.exception.order;

import cn.bootx.common.core.exception.BizException;

import static cn.bootx.ordercenter.code.OrderCenterErrorCode.ORDER_PAYING;

/**   
* 订单已付款
* @author xxm  
* @date 2020/12/10 
*/
public class OrderPayingException extends BizException {

    public OrderPayingException() {
        super(ORDER_PAYING, "订单已付款");
    }
}
