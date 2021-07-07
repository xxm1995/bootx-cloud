package cn.bootx.goodscenter.core.sku.factory;

import cn.bootx.goodscenter.core.sku.entity.GoodsSku;
import cn.bootx.goodscenter.dto.sku.GoodsSkuDto;
import cn.bootx.goodscenter.param.sku.CreateSkuParam;
import cn.bootx.goodscenter.param.sku.UpdateSkuParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
* sku工厂
* @author xxm  
* @date 2021/2/2 
*/
@Component
@RequiredArgsConstructor
public class GoodsSkuFactory {

    /**
     * 构建
     */
    public GoodsSku construct(CreateSkuParam skuParam){
        GoodsSku goodsSku = new GoodsSku();
        BeanUtils.copyProperties(skuParam,goodsSku);
        goodsSku.setAttrDefIds(String.join(",", skuParam.getAttrDefIdList()));
        goodsSku.setAttrValues(String.join(",", skuParam.getAttrValueList()));
        goodsSku.setAttrValueDisplays(String.join(",", skuParam.getAttrValueDisplayList()));
        return goodsSku;
    }

    /**
     * 构建
     */
    public GoodsSku construct(UpdateSkuParam skuParam){
        GoodsSku goodsSku = new GoodsSku();
        BeanUtils.copyProperties(skuParam,goodsSku);
        goodsSku.setAttrDefIds(String.join(",", skuParam.getAttrDefIdList()));
        goodsSku.setAttrValues(String.join(",", skuParam.getAttrValueList()));
        goodsSku.setAttrValueDisplays(String.join(",", skuParam.getAttrValueDisplayList()));
        return goodsSku;
    }

    /**
     * 转换成DTO
     */
    public List<GoodsSkuDto> convertGoodsToDto(List<GoodsSku> poList) {
        return poList.stream()
                .map(GoodsSku::toDto)
                .collect(Collectors.toList());
    }
}
