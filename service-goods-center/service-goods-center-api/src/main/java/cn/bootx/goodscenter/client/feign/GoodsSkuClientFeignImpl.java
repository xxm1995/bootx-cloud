package cn.bootx.goodscenter.client.feign;

import cn.bootx.goodscenter.client.GoodsSkuClient;
import cn.bootx.goodscenter.dto.sku.GoodsSkuDto;
import cn.bootx.goodscenter.param.sku.CreateSkuParam;
import cn.bootx.goodscenter.param.sku.GenSkuParam;
import lombok.RequiredArgsConstructor;
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
    public GoodsSkuDto getById(Long id) {
        return goodsSkuFeign.getById(id).getData();
    }

    @Override
    public List<GoodsSkuDto> findBySkuIds(List<Long> skuIds) {
        return goodsSkuFeign.findBySkuIds(skuIds).getData();
    }

    @Override
    public List<GoodsSkuDto> genGoodsSkuBySkuAttrs(GenSkuParam genSkuParam) {
        return goodsSkuFeign.genSku(genSkuParam).getData();
    }

    @Override
    public List<GoodsSkuDto> findByGoodsId(Long goodsId) {
        return goodsSkuFeign.findByGoodsId(goodsId).getData();
    }

    @Override
    public GoodsSkuDto add(CreateSkuParam skuParam) {
        return goodsSkuFeign.add(skuParam).getData();
    }
}
