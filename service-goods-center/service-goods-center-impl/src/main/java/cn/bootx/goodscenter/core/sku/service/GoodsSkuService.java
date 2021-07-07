package cn.bootx.goodscenter.core.sku.service;

import cn.bootx.common.web.exception.BizException;
import cn.bootx.common.web.util.ResultConvertUtils;
import cn.bootx.goodscenter.core.attributes.handler.AttrGenHandler;
import cn.bootx.goodscenter.core.goods.entity.Goods;
import cn.bootx.goodscenter.core.sku.dao.GoodsSkuAttrManager;
import cn.bootx.goodscenter.core.sku.dao.GoodsSkuAttrRepository;
import cn.bootx.goodscenter.core.sku.dao.GoodsSkuManager;
import cn.bootx.goodscenter.core.sku.dao.GoodsSkuRepository;
import cn.bootx.goodscenter.core.sku.entity.GoodsSku;
import cn.bootx.goodscenter.core.sku.entity.GoodsSkuAttr;
import cn.bootx.goodscenter.core.sku.factory.GoodsSkuFactory;
import cn.bootx.goodscenter.dto.sku.GoodsSkuAttrDto;
import cn.bootx.goodscenter.dto.sku.GoodsSkuDto;
import cn.bootx.goodscenter.exception.sku.SkuNotFoundException;
import cn.bootx.goodscenter.param.sku.CreateSkuParam;
import cn.bootx.starter.snowflake.SnowFlakeId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * sku管理
 * @author xxm
 * @date 2020/11/20
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GoodsSkuService {
    private final GoodsSkuRepository skuRepository;
    private final GoodsSkuManager skuManager;
    private final GoodsSkuAttrManager skuAttrManager;

    private final GoodsSkuAttrRepository skuAttrRepository;
    private final GoodsSkuFactory goodsSkuFactory;

    private final SnowFlakeId snowFlakeId;

    /**
     * 根据 id 获取相应的商品SKU
     */
    public GoodsSkuDto getById(Long id) {
        GoodsSku goodsSku = skuManager.findById(id).orElseThrow(SkuNotFoundException::new);
        return goodsSku.toDto();
    }

    /**
     * 根据goodsId查询
     */
    public List<GoodsSkuDto> findByGoodsId(Long goodsId){
        return ResultConvertUtils.dtoListConvert(skuManager.findByGoodsId(goodsId));
    }

    /**
     * 批量查询sku
     */
    public List<GoodsSkuDto> findBySkuIds(List<Long> skuIds){
        return ResultConvertUtils.dtoListConvert(skuManager.findByIds(skuIds));
    }

    /**
     * 查询sku对应的属性定义
     */
    public List<GoodsSkuAttrDto> findAttr(Long skuId){
        return ResultConvertUtils.dtoListConvert(skuAttrManager.findBySkuId(skuId));
    }

    /**
     * 上架商品SKU
     */
    @Transactional(rollbackFor = Exception.class)
    public void saleOn(Long skuId){
        GoodsSku sku = skuManager.findById(skuId).orElseThrow(() -> new BizException("商品sku不存在"));
        sku.setSaleState(1)
                .setSaleOnTime(LocalDateTime.now());
        skuRepository.save(sku);
    }

    /**
     * 下架商品SKU
     */
    @Transactional(rollbackFor = Exception.class)
    public void saleOff(Long skuId){
        GoodsSku sku = skuManager.findById(skuId).orElseThrow(() -> new BizException("商品sku不存在"));
        sku.setSaleState(2)
                .setSaleOnTime(LocalDateTime.now());
        skuRepository.save(sku);
    }

    /**
     * 批量新增商品的SKU和相关属性
     */
    public List<GoodsSku> addSkus(List<CreateSkuParam> skuParams, Goods goods){
        List<GoodsSku> skus = skuParams.stream()
                .map(skuParam -> {
                    GoodsSku goodsSku = goodsSkuFactory.construct(skuParam);
                    goodsSku.setGoodsId(goods.getId())
                            .setPacking(false)
                            .setCid(goods.getCid());
                    goodsSku.setId(snowFlakeId.nextId());
                    return goodsSku;
                }).collect(Collectors.toList());

        List<GoodsSkuAttr> goodsSkuAttrs = AttrGenHandler.genSkuAttrsBySkus(skus);
        List<GoodsSku> goodsSkus = skuRepository.saveAll(skus);
        skuAttrRepository.saveAll(goodsSkuAttrs);
        return goodsSkus;
    }

    /**
     * 新增商品的SKU和相关属性
     */
    public GoodsSku addSku(CreateSkuParam skuParam, Goods goods){
        GoodsSku goodsSku = goodsSkuFactory.construct(skuParam);
        goodsSku.setGoodsId(goods.getId())
                .setPacking(false)
                .setCid(goods.getCid());
        skuRepository.save(goodsSku);
        List<GoodsSkuAttr> goodsSkuAttrs = AttrGenHandler.genSkuAttrsBySku(goodsSku);
        skuAttrRepository.saveAll(goodsSkuAttrs);
        return goodsSku;
    }

}
