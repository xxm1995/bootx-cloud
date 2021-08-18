package cn.bootx.goods.client.feign;

import cn.bootx.goods.client.GoodsClient;
import cn.bootx.goods.dto.goods.GoodsDto;
import cn.bootx.goods.param.goods.GoodsParam;
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
    public GoodsDto add(GoodsParam param) {
        return goodsFeign.add(param).getData();
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

    @Override
    public GoodsDto findById(Long id) {
        return goodsFeign.findById(id).getData();
    }
}
