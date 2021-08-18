package cn.bootx.goods.client;

import cn.bootx.goods.dto.goods.GoodsDto;
import cn.bootx.goods.param.goods.GoodsParam;

import java.util.List;

/**   
* 商品管理
* @author xxm  
* @date 2020/11/26 
*/
public interface GoodsClient {
    /**
     * 添加商品
     */
    GoodsDto add(GoodsParam param);

    /**
     * 查询全部
     */
    List<GoodsDto> findAll();

    /**
     * 按照类目查询
     */
    List<GoodsDto> findByCategory(Long cid);

    /**
     * 获取商品详情包括sku
     */
    GoodsDto getDetails(Long id);

    /**
     * 获取商品信息
     */
    GoodsDto findById(Long id);
}
