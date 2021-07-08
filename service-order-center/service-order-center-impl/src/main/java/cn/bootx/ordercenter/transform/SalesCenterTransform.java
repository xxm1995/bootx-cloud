package cn.bootx.ordercenter.transform;

import cn.bootx.ordercenter.param.order.OrderParam;
import cn.bootx.salescenter.param.order.OrderCheckParam;
import cn.hutool.core.bean.BeanUtil;
import org.springframework.stereotype.Component;

/**
* 销售中心对象转换
* @author xxm
* @date 2021/2/17
*/
@Component
public class SalesCenterTransform {

    /**
     * 转化成销售中心所需参数
     */
    public OrderCheckParam buildCheckOrder(OrderParam orderParam){
        OrderCheckParam orderCheckParam = new OrderCheckParam();
        BeanUtil.copyProperties(orderParam,orderCheckParam);
        return orderCheckParam;
    }
}
