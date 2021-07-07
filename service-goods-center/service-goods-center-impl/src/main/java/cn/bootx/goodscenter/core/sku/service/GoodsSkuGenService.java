package cn.bootx.goodscenter.core.sku.service;

import cn.bootx.goodscenter.core.sku.dao.GoodsSkuManager;
import cn.bootx.goodscenter.core.sku.entity.GoodsSku;
import cn.bootx.goodscenter.core.sku.factory.GoodsSkuFactory;
import cn.bootx.goodscenter.core.attributes.handler.AttrGenHandler;
import cn.bootx.goodscenter.core.category.handler.ValueDisplayPair;
import cn.bootx.goodscenter.dto.sku.GoodsSkuDto;
import cn.bootx.goodscenter.param.sku.GenSkuAttrParam;
import cn.bootx.goodscenter.param.sku.GenSkuParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 商品sku生成Service
 * @author xxm
 * @date 2021/2/2
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GoodsSkuGenService {
    private final GoodsSkuManager goodsSkuManager;
    private final GoodsSkuFactory goodsSkuFactory;

    /**
     * 根据商品 SKU 属性生成对应的商品 SKU
     *
     * 若商品 SKU 已存在，则直接使用已存在的数据；
     * 若商品 SKU 未存在，则创建一个内存级的 SKU DTO。
     */
    public List<GoodsSkuDto> genSkuByAttrs(GenSkuParam genSkuParam){
        List<GoodsSkuDto> goodsSkus = new ArrayList<>();
        List<GenSkuAttrParam> skuAttrList = genSkuParam.getSkuAttrList();
        Long goodsId = genSkuParam.getGoodsId();

        List<List<ValueDisplayPair>> skuAttrValues = AttrGenHandler.extractSkuAttrValues(skuAttrList);

        // 1,形成笛卡尔积的形式，如 1_a_X 形式（key：所有属性值拼接的字符串，value:所有属性显示值拼接的字符串）
        Map<String, String> valueMap = AttrGenHandler.calculateDescartes(skuAttrValues);
        List<String> attrValues = new ArrayList<>(valueMap.keySet());

        // 2,根据goodsId和属性值列表，查询所有已存在的商品SKU
        String attrDefIds = this.extractSkuAttrDefIds(skuAttrList);

        // 3,根据goodsId和属性值列表，查询所有已存在的商品SKU
        if (Objects.nonNull(goodsId)) {
            List<GoodsSku> goodsSkuList = goodsSkuManager.findByGoodsIdAndAttrValuesIn(goodsId, attrValues);
            // 添加已存在的
            goodsSkus.addAll(goodsSkuFactory.convertGoodsToDto(goodsSkuList));

            // 去掉所有已存在的项
            for (GoodsSku goodsSku : goodsSkuList) {
                attrValues.remove(goodsSku.getAttrValues());
            }
        }

        // 4,为剩下的项创建对应的 GoodsSkuDto
        for (String attrValue : attrValues) {
            GoodsSkuDto skuDto = new GoodsSkuDto()
                    .setGoodsId(goodsId)
                    .setAttrDefIds(attrDefIds)
                    .setAttrValues(attrValue)
                    .setAttrValueDisplays(valueMap.get(attrValue));
            goodsSkus.add(skuDto);
        }
        return goodsSkus;
    }

    /**
     * 提取 SkuAttrDefIds(属性定义id数组) 属性
     */
    private String extractSkuAttrDefIds(List<GenSkuAttrParam> skuAttrList) {
        StringBuilder result = new StringBuilder();
        for (GenSkuAttrParam skuAttrParam : skuAttrList) {
            result.append(",").append(skuAttrParam.getAttrDefId());
        }
        return result.substring(1);
    }
}
