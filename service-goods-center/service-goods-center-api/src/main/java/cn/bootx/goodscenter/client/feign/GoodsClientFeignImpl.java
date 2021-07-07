package cn.bootx.goodscenter.client.feign;

import cn.bootx.goodscenter.client.GoodsClient;
import cn.bootx.goodscenter.dto.goods.GoodsDto;
import cn.bootx.goodscenter.dto.sku.GoodsSkuDto;
import cn.bootx.goodscenter.param.goods.CreateGoodsAndSkuParam;
import cn.bootx.goodscenter.param.sku.CreateSkuParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnMissingBean(GoodsClient.class)
@RequiredArgsConstructor
public class GoodsClientFeignImpl implements GoodsClient {
    @Autowired(required = false)
    private GoodsFeign goodsFeign;

    @Override
    public GoodsSkuDto addSingle(CreateSkuParam param) {
        return goodsFeign.addSingle(param).getData();
    }

    @Override
    public GoodsDto add(CreateGoodsAndSkuParam param) {
        return goodsFeign.add(param).getData();
    }

    @Override
    public GoodsDto addPackGoods(CreateGoodsAndSkuParam param) {
        return goodsFeign.addPackGoods(param).getData();
    }

    @Override
    public List<GoodsDto> findAll() {
        return goodsFeign.findAll().getData();
    }

    @Override
    public List<GoodsDto> findByCategory(Long cid) {
        return goodsFeign.findByCategory(cid).getData();
    }

    @Override
    public GoodsDto getDetails(Long id) {
        return goodsFeign.getDetails(id).getData();
    }
}
