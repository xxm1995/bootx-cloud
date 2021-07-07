package cn.bootx.goodscenter.client;

import cn.bootx.goodscenter.dto.sku.GoodsSkuDto;
import cn.bootx.goodscenter.param.sku.CreateSkuParam;
import cn.bootx.goodscenter.param.sku.GenSkuParam;

import java.util.List;

/**   
* sku 操作
* @author xxm  
* @date 2020/11/26 
*/
public interface GoodsSkuClient {
    /**
     * 根据 id 获取相应的商品SKU
     */
    GoodsSkuDto getById(Long id);


    /**
     * 批量查询
     */
    List<GoodsSkuDto> findBySkuIds(List<Long> skuIds);

    /**
     * 根据商品 SKU 属性生成对应的商品 SKU 预览
     */
    List<GoodsSkuDto> genGoodsSkuBySkuAttrs(GenSkuParam genSkuParam);

    /**
     * 根据goodsId查询
     */
    List<GoodsSkuDto> findByGoodsId(Long goodsId);

    /**
     * 添加商品sku
     */
    GoodsSkuDto add(CreateSkuParam skuParam);
}
