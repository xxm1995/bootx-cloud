package cn.bootx.goodscenter.core.goods.service;

import cn.bootx.common.web.util.ResultConvertUtils;
import cn.bootx.goodscenter.core.attributes.handler.AttrGenHandler;
import cn.bootx.goodscenter.core.goods.dao.GoodsAttrRepository;
import cn.bootx.goodscenter.core.goods.dao.GoodsRepository;
import cn.bootx.goodscenter.core.goods.entity.Goods;
import cn.bootx.goodscenter.core.goods.entity.GoodsAttr;
import cn.bootx.goodscenter.core.goods.factory.GoodsFactory;
import cn.bootx.goodscenter.core.packing.service.GoodsSkuPackService;
import cn.bootx.goodscenter.core.sku.entity.GoodsSku;
import cn.bootx.goodscenter.core.sku.service.GoodsSkuService;
import cn.bootx.goodscenter.dto.goods.GoodsDto;
import cn.bootx.goodscenter.dto.sku.GoodsSkuDto;
import cn.bootx.goodscenter.param.goods.CreateGoodsAndSkuParam;
import cn.bootx.goodscenter.param.goods.CreateGoodsParam;
import cn.bootx.goodscenter.param.sku.CreateSkuParam;
import cn.bootx.starter.snowflake.SnowFlakeId;
import cn.hutool.core.bean.BeanUtil;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * 创建商品Service
 * @author xxm
 * @date 2021/2/2
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GoodsAddService {
    private final GoodsFactory goodsFactory;
    private final GoodsSkuPackService skuPackService;
    private final GoodsSkuService skuService;

    private final GoodsRepository goodsRepository;
    private final GoodsAttrRepository goodsAttrRepository;

    private final SnowFlakeId snowFlakeId;


    /**
     * 增加单品, 一个sku对应一个商品
     */
    @GlobalTransactional(rollbackFor = Exception.class)
    public GoodsSkuDto addSingle(CreateSkuParam param){
        CreateGoodsParam goodsParam = new CreateGoodsParam();
        BeanUtil.copyProperties(param,goodsParam);
        Goods goods = this.addGoods(goodsParam);
        // 添加SKU
        GoodsSku sku = skuService.addSku(param, goods);
        this.addGoodsPrice(goods, Collections.singletonList(sku));
        goodsRepository.save(goods);

        // 返回值构建
        return sku.toDto();
    }


    /**
     * 新增商品和sku
     */
    @GlobalTransactional(rollbackFor = Exception.class)
    public GoodsDto addGoodsAndSku(CreateGoodsAndSkuParam param){
        // 添加商品
        CreateGoodsParam goodsParam = param.getGoodsParam();
        Goods goods = this.addGoods(goodsParam);
        // 添加SKU
        List<CreateSkuParam> skuParams = param.getSkuParams();
        List<GoodsSku> skus = skuService.addSkus(skuParams, goods);

        this.addGoodsPrice(goods,skus);
        goodsRepository.save(goods);

        // 返回值构建
        GoodsDto goodsDto = goods.toDto();
        goodsDto.setSkus(ResultConvertUtils.dtoListConvert(skus));
        return goodsDto;
    }
    /**
     * 新增商品
     */
    public Goods addGoods(CreateGoodsParam goodsParam){
        Goods goods = goodsFactory.construct(goodsParam);
        goods.setPacking(false)
                .setId(snowFlakeId.nextId());
        List<GoodsAttr> goodsAttrs = AttrGenHandler.genGoodsAttrsByGoods(goods);
        goodsAttrRepository.saveAll(goodsAttrs);
        return goods;
    }

    /**
     * 新增打包品和打包sku
     */
    public GoodsDto addPackGoodsAndSku(CreateGoodsAndSkuParam param){
        // 添加商品
        CreateGoodsParam goodsParam = param.getGoodsParam();
        Goods goods = goodsFactory.construct(goodsParam);
        goods.setPacking(true)
                .setId(snowFlakeId.nextId());

        // 添加sku 及 打包sku
        List<CreateSkuParam> skuParams = param.getSkuParams();
        List<GoodsSku> skus = skuPackService.addPackSkus(skuParams, goods);

        this.addGoodsPrice(goods,skus);
        goodsRepository.save(goods);

        // 返回值构建
        GoodsDto goodsDto = goods.toDto();
        goodsDto.setSkus(ResultConvertUtils.dtoListConvert(skus));
        return goodsDto;
    }


    /**
     * 添加时计算商品价格
     */
    private void addGoodsPrice(Goods goods, List<GoodsSku> skus){
        BigDecimal min = skus.stream()
                .map(GoodsSku::getPrice)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        BigDecimal max = skus.stream()
                .map(GoodsSku::getPrice)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        goods.setDisplayLowerPrice(min)
                .setDisplayUpperPrice(max);
    }

}
