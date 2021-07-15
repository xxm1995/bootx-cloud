package cn.bootx.salescenter.core.match.handler.impl.feature;

import cn.bootx.salescenter.core.match.handler.func.FeatureHandler;
import cn.bootx.salescenter.param.order.OrderDetailCheckParam;
import org.springframework.stereotype.Component;

/**   
* sku特征点提取
* @author xxm  
* @date 2021/5/18 
*/
@Component
public class SkuFeatureHandler implements FeatureHandler {

    @Override
    public String getFeatureType() {
        return "skuId";
    }

    @Override
    public String getFeaturePoint(OrderDetailCheckParam checkParam) {
        return String.valueOf(checkParam.getSkuId());
    }
}
