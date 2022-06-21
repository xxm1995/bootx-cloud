package cn.bootx.goodscenter.client;

import cn.bootx.goodscenter.dto.sku.GoodsSkuDto;

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
    GoodsSkuDto findById(Long id);

    /**
     * 批量查询
     */
    List<GoodsSkuDto> findBySkuIds(List<Long> skuIds);

    /**
     * 根据goodsId查询
     */
    List<GoodsSkuDto> findByGoodsId(Long goodsId);
}
