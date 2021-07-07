package cn.bootx.goodscenter.core.sku.service;

import cn.bootx.goodscenter.core.attributes.handler.AttrGenHandler;
import cn.bootx.goodscenter.core.sku.dao.GoodsSkuAttrRepository;
import cn.bootx.goodscenter.core.sku.dao.GoodsSkuRepository;
import cn.bootx.goodscenter.core.sku.entity.GoodsSku;
import cn.bootx.goodscenter.core.sku.entity.GoodsSkuAttr;
import cn.bootx.goodscenter.core.sku.factory.GoodsSkuFactory;
import cn.bootx.goodscenter.dto.sku.GoodsSkuDto;
import cn.bootx.goodscenter.param.sku.CreateSkuParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 商品sku操作类
* @author xxm
* @date 2021/5/6
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class GoodsSkuOperateService {
    private final GoodsSkuFactory goodsSkuFactory;
    private final GoodsSkuRepository goodsSkuRepository;
    private final GoodsSkuAttrRepository skuAttrRepository;

    /**
     * 添加sku
     */
    public GoodsSkuDto add(CreateSkuParam skuParam){
        GoodsSku goodsSku = goodsSkuFactory.construct(skuParam);
        goodsSku.setPacking(false);
        goodsSkuRepository.save(goodsSku);
        List<GoodsSkuAttr> goodsSkuAttrs = AttrGenHandler.genSkuAttrsBySku(goodsSku);
        skuAttrRepository.saveAll(goodsSkuAttrs);
        return goodsSku.toDto();
    }
}
