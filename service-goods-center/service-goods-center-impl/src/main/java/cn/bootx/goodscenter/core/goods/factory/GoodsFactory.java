package cn.bootx.goodscenter.core.goods.factory;

import cn.bootx.goodscenter.core.goods.entity.Goods;
import cn.bootx.goodscenter.dto.goods.GoodsAttrDto;
import cn.bootx.goodscenter.param.goods.CreateGoodsParam;
import cn.bootx.goodscenter.param.goods.UpdateGoodsParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


/**   
* 商品工厂
* @author xxm  
* @date 2021/2/2 
*/
@Component
@RequiredArgsConstructor
public class GoodsFactory {

    /**
     * 生成商品
     */
    public Goods construct(CreateGoodsParam createGoodsParam){
        Goods goods = new Goods();
        BeanUtils.copyProperties(createGoodsParam,goods);
        List<GoodsAttrDto> attrList = createGoodsParam.getAttrList();
        String attrDefIds = attrList.stream().map(GoodsAttrDto::getAttrDefId).map(String::valueOf).collect(Collectors.joining(","));
        String attrValues = attrList.stream().map(GoodsAttrDto::getAttrValue).collect(Collectors.joining(","));
        String attrValueDisplays = attrList.stream().map(GoodsAttrDto::getAttrValueDisplay).collect(Collectors.joining(","));

        goods.setAttrDefIds(attrDefIds);
        goods.setAttrValues(attrValues);
        goods.setAttrValueDisplays(attrValueDisplays);
        return goods;
    }

    /**
     * 生成商品
     */
    public Goods construct(UpdateGoodsParam createGoodsParam){
        Goods goods = new Goods();
        BeanUtils.copyProperties(createGoodsParam,goods);
        List<GoodsAttrDto> attrList = createGoodsParam.getAttrList();
        String attrDefIds = attrList.stream().map(GoodsAttrDto::getAttrDefId).map(String::valueOf).collect(Collectors.joining(","));
        String attrValues = attrList.stream().map(GoodsAttrDto::getAttrValue).collect(Collectors.joining(","));
        String attrValueDisplays = attrList.stream().map(GoodsAttrDto::getAttrValueDisplay).collect(Collectors.joining(","));

        goods.setAttrDefIds(attrDefIds);
        goods.setAttrValues(attrValues);
        goods.setAttrValueDisplays(attrValueDisplays);
        return goods;
    }
}
