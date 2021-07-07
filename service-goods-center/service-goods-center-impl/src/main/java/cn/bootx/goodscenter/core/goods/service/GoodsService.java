package cn.bootx.goodscenter.core.goods.service;

import cn.bootx.common.web.util.ResultConvertUtils;
import cn.bootx.goodscenter.core.goods.dao.GoodsManager;
import cn.bootx.goodscenter.core.goods.entity.Goods;
import cn.bootx.goodscenter.core.sku.dao.GoodsSkuManager;
import cn.bootx.goodscenter.dto.goods.GoodsDto;
import cn.bootx.goodscenter.dto.sku.GoodsSkuDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author xxm
 * @date 2020/11/20
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GoodsService {
    private final GoodsManager goodsManager;
    private final GoodsSkuManager goodsSkuManager;

    /**
     * 查询全部
     */
    public List<GoodsDto> findAll(){
        return ResultConvertUtils.dtoListConvert(goodsManager.findAll());
    }

    /**
     * 查询商品详情
     */
    public GoodsDto getDetails(Long goodsId){
        GoodsDto goodsDto = goodsManager.findById(goodsId).map(Goods::toDto).orElse(null);
        if (Objects.nonNull(goodsDto)){
            // 查询sku
            List<GoodsSkuDto> goodsSkus = ResultConvertUtils.dtoListConvert(goodsSkuManager.findByGoodsId(goodsId));
            goodsDto.setSkus(goodsSkus);
        }
        return goodsDto;
    }


    /**
     * 获取商品信息
     */
    public GoodsDto getInfo(Long goodsId){
        return goodsManager.findById(goodsId).map(Goods::toDto).orElse(null);
    }

    /**
     * 按照类目查询商品
     */
    public List<GoodsDto> findByCategory(Long cid){
        return ResultConvertUtils.dtoListConvert(goodsManager.findByCid(cid));
    }

    /**
     * 获取商品显示的价格下限
     */
    public BigDecimal getGoodsLowerPrice(Long goodsId){
        return goodsManager.getGoodsLowerPrice(goodsId);
    }

    /**
     * 获取商品显示的价格上限
     */
    public BigDecimal getGoodsUpperPrice(Long goodsId){
        return goodsManager.getGoodsUpperPrice(goodsId);

    }

}
