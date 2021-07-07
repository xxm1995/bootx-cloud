package cn.bootx.goodscenter.core.attributes.handler;

import cn.bootx.common.web.exception.ValidationFailedException;
import cn.bootx.goodscenter.core.category.handler.Descartes;
import cn.bootx.goodscenter.core.category.handler.ValueDisplayPair;
import cn.bootx.goodscenter.core.goods.entity.Goods;
import cn.bootx.goodscenter.core.goods.entity.GoodsAttr;
import cn.bootx.goodscenter.core.sku.entity.GoodsSku;
import cn.bootx.goodscenter.core.sku.entity.GoodsSkuAttr;
import cn.bootx.goodscenter.param.sku.GenSkuAttrParam;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;

import java.util.*;
import java.util.stream.Collectors;

/**   
* 属性生成Handler
* @author xxm  
* @date 2020/11/21 
*/
@UtilityClass
public class AttrGenHandler {

    /**
     * 提取所有sku属性值和属性显示值之间的映射关系
     * （属性值列表和属性值展示列表的存放顺序需保持一致，并且存放数量相同）
     */
    public static List<List<ValueDisplayPair>> extractSkuAttrValues(List<GenSkuAttrParam> skuAttrList) {
        List<List<ValueDisplayPair>> values = new ArrayList<>();
        for (GenSkuAttrParam skuAttrParam : skuAttrList) {
            List<String> attrValueList = skuAttrParam.getAttrValues();
            List<String> attrValueListDisplay = skuAttrParam.getAttrValueDisplays();

            if (CollectionUtil.isEmpty(attrValueList)
                    || CollectionUtil.isEmpty(attrValueListDisplay)
                    || (attrValueList.size() != attrValueListDisplay.size())) {
                throw new ValidationFailedException("SKU属性值数据异常");
            }
            values.add(toIdValuePairList(attrValueList, attrValueListDisplay));
        }
        return values;
    }

    /**
     * 构造映射关系，属性值 -> 属性显示值
     *
     */
    private static List<ValueDisplayPair> toIdValuePairList(List<String> attrValueList, List<String> attrValueListDisplay) {
        List<ValueDisplayPair> result = new ArrayList<>();

        for (int i = 0; i < attrValueList.size(); i++) {
            result.add(new ValueDisplayPair(attrValueList.get(i), attrValueListDisplay.get(i)));
        }
        return result;
    }

    /**
     * 进行笛卡尔积后，对属性值和属性显示值进行处理。
     *
     * @return key:所有属性值拼接的字符串，属性值之间以_间隔。value:所有属性显示值拼接的字符串，属性显示值之间以_间隔。
     */
    public static Map<String, String> calculateDescartes(List<List<ValueDisplayPair>> skuAttrValues) {
        List<List<ValueDisplayPair>> result = new ArrayList<>();
        Descartes.recursive(skuAttrValues, result, 0, new ArrayList<>());

        Map<String, String> strValueStrMap = new HashMap<>(result.size());

        for (List<ValueDisplayPair> valuePairList : result) {
            StringBuilder valStr = new StringBuilder();
            StringBuilder displayStr = new StringBuilder();
            for (ValueDisplayPair idValuePair : valuePairList) {
                valStr.append(",").append(idValuePair.getValue());
                displayStr.append(",").append(idValuePair.getDisplay());
            }

            String val = valStr.substring(1);
            String display = displayStr.substring(1);

            strValueStrMap.put(val, display);
        }
        return strValueStrMap;
    }


    /**
     * 批量生成sku 属性
     */
    public static List<GoodsSkuAttr> genSkuAttrsBySkus(List<GoodsSku> skuList){
        List<GoodsSkuAttr> skuAttrs = new ArrayList<>();
        for (GoodsSku goodsSku : skuList) {
            skuAttrs.addAll(genSkuAttrsBySku(goodsSku));
        }
        return skuAttrs;
    }

    /**
     * 生成 sku 属性
     */
    public static List<GoodsSkuAttr> genSkuAttrsBySku(GoodsSku goodsSku) {
        List<GoodsSkuAttr> skuAttrs = new ArrayList<>();

        String attrDefIdsString = goodsSku.getAttrDefIds();
        String attrValuesString = goodsSku.getAttrValues();
        String attrValueDisplaysString = goodsSku.getAttrValueDisplays();

        List<String> attrDefIds = splitAppendList(attrDefIdsString);
        List<String> attrValues = splitAppendList(attrValuesString);
        List<String> attrValueDisplays = splitAppendList(attrValueDisplaysString);

        for (int i = 0; i < attrDefIds.size(); i++) {
            GoodsSkuAttr skuAttr = new GoodsSkuAttr();
            skuAttr.setCid(goodsSku.getCid());
            skuAttr.setGoodsId(goodsSku.getGoodsId());
            skuAttr.setSkuId(goodsSku.getId());
            skuAttr.setAttrDefId(Long.parseLong(attrDefIds.get(i)));
            skuAttr.setAttrValue(attrValues.get(i));
            skuAttr.setAttrValueDisplays(attrValueDisplays.get(i));
            skuAttrs.add(skuAttr);
        }
        return skuAttrs;
    }

    /**
     * 批量生成商品属性
     */
//    public static List<GoodsAttr> genGoodsAttrsByGoodies(List<Goods> goodies){
//        List<GoodsAttr> goodsAttrs = new ArrayList<>();
//        for (Goods goods : goodies) {
//            goodsAttrs.addAll(genGoodsAttrsByGoods(goods));
//        }
//        return goodsAttrs;
//    }

    /**
     * 生成商品属性
     */
    public static List<GoodsAttr> genGoodsAttrsByGoods(Goods goodsSku) {
        List<GoodsAttr> goodsAttrs = new ArrayList<>();

        String attrDefIdsString = goodsSku.getAttrDefIds();
        String attrValuesString = goodsSku.getAttrValues();
        String attrValueDisplaysString = goodsSku.getAttrValueDisplays();

        List<String> attrDefIds = splitAppendList(attrDefIdsString);
        List<String> attrValues = splitAppendList(attrValuesString);
        List<String> attrValueDisplays = splitAppendList(attrValueDisplaysString);

        for (int i = 0; i < attrDefIds.size(); i++) {
            GoodsAttr skuAttr = new GoodsAttr();
            skuAttr.setCid(goodsSku.getCid());
            skuAttr.setGoodsId(goodsSku.getId());
            skuAttr.setAttrDefId(Long.parseLong(attrDefIds.get(i)));
            skuAttr.setAttrValue(attrValues.get(i));
            skuAttr.setAttrValueDisplays(attrValueDisplays.get(i));
            goodsAttrs.add(skuAttr);
        }
        return goodsAttrs;
    }

    /**
     * 将拼接的字符串拆分成数组形式
     */
    public static List<String> splitAppendList(String appendString) {
        if (StrUtil.isEmpty(appendString)) {
            return new ArrayList<>(0);
        }
        return Arrays.stream(appendString.split(","))
                .collect(Collectors.toList());
    }
}
