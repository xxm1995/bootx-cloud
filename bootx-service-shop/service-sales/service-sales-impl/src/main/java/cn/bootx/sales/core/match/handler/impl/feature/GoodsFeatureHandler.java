package cn.bootx.sales.core.match.handler.impl.feature;

import cn.bootx.sales.core.match.handler.func.FeatureHandler;
import cn.bootx.sales.param.order.OrderDetailCheckParam;
import org.springframework.stereotype.Component;

/**   
* ååidæ½å
* @author xxm  
* @date 2021/5/18 
*/
@Component
public class GoodsFeatureHandler implements FeatureHandler {

    @Override
    public String getFeatureType() {
        return "goodsId";
    }

    @Override
    public String getFeaturePoint(OrderDetailCheckParam checkParam) {
        return String.valueOf(checkParam.getGoodsId());
    }
}
