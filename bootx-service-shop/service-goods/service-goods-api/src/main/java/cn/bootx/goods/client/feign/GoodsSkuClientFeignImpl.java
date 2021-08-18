package cn.bootx.goods.client.feign;

import cn.bootx.goods.client.GoodsSkuClient;
import cn.bootx.goods.dto.sku.GoodsSkuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnMissingBean(GoodsSkuClient.class)
public class GoodsSkuClientFeignImpl implements GoodsSkuClient {
    @Autowired(required = false)
    private GoodsSkuFeign goodsSkuFeign;

    @Override
    public GoodsSkuDto findById(Long id) {
        return goodsSkuFeign.findById(id).getData();
    }

    @Override
    public List<GoodsSkuDto> findBySkuIds(List<Long> skuIds) {
        return goodsSkuFeign.findBySkuIds(skuIds).getData();
    }

    @Override
    public List<GoodsSkuDto> findByGoodsId(Long goodsId) {
        return goodsSkuFeign.findByGoodsId(goodsId).getData();
    }

}
