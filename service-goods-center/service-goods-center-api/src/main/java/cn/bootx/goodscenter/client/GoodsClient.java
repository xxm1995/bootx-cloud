package cn.bootx.goodscenter.client;

import cn.bootx.goodscenter.dto.goods.GoodsDto;
import cn.bootx.goodscenter.dto.sku.GoodsSkuDto;
import cn.bootx.goodscenter.param.goods.CreateGoodsAndSkuParam;
import cn.bootx.goodscenter.param.sku.CreateSkuParam;

import java.util.List;

/**   
* 商品管理
* @author xxm  
* @date 2020/11/26 
*/
public interface GoodsClient {

    /**
     * 添加单品
     */
    GoodsSkuDto addSingle( CreateSkuParam param);
    /**
     * 添加商品
     */
    GoodsDto add(CreateGoodsAndSkuParam param);

    /**
     * 添加打包商品
     */
    GoodsDto addPackGoods(CreateGoodsAndSkuParam param);

    /**
     * 查询全部
     */
    List<GoodsDto> findAll();

    /**
     * 按照类目查询
     */
    List<GoodsDto> findByCategory(Long cid);

    /**
     * 获取商品详情
     */
    GoodsDto getDetails(Long id);
}
