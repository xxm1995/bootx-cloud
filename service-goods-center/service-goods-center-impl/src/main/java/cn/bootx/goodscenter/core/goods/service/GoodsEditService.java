package cn.bootx.goodscenter.core.goods.service;

import cn.bootx.common.web.exception.BizException;
import cn.bootx.goodscenter.core.attributes.handler.AttrGenHandler;
import cn.bootx.goodscenter.core.goods.dao.GoodsAttrRepository;
import cn.bootx.goodscenter.core.goods.dao.GoodsManager;
import cn.bootx.goodscenter.core.goods.dao.GoodsRepository;
import cn.bootx.goodscenter.core.goods.entity.Goods;
import cn.bootx.goodscenter.core.goods.factory.GoodsFactory;
import cn.bootx.goodscenter.core.packing.dao.GoodsSkuPackManager;
import cn.bootx.goodscenter.core.packing.dao.GoodsSkuPackRepository;
import cn.bootx.goodscenter.core.packing.service.GoodsSkuPackService;
import cn.bootx.goodscenter.core.sku.dao.GoodsSkuAttrManager;
import cn.bootx.goodscenter.core.sku.dao.GoodsSkuAttrRepository;
import cn.bootx.goodscenter.core.sku.dao.GoodsSkuManager;
import cn.bootx.goodscenter.core.sku.dao.GoodsSkuRepository;
import cn.bootx.goodscenter.core.sku.entity.GoodsSku;
import cn.bootx.goodscenter.core.sku.entity.GoodsSkuAttr;
import cn.bootx.goodscenter.core.sku.factory.GoodsSkuFactory;
import cn.bootx.goodscenter.core.sku.service.GoodsSkuService;
import cn.bootx.goodscenter.param.goods.UpdateGoodsAndSkuParam;
import cn.bootx.goodscenter.param.goods.UpdateGoodsParam;
import cn.bootx.goodscenter.param.sku.CreateSkuParam;
import cn.bootx.goodscenter.param.sku.UpdateSkuParam;
import cn.bootx.starter.snowflake.SnowFlakeId;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 编辑商品Service
 * @author xxm
 * @date 2021/2/2
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GoodsEditService {
    private final GoodsFactory goodsFactory;
    private final GoodsSkuFactory goodsSkuFactory;
    private final GoodsSkuPackService skuPackService;
    private final GoodsSkuService skuService;

    private final GoodsRepository goodsRepository;
    private final GoodsAttrRepository goodsAttrRepository;
    private final GoodsManager goodsManager;
    private final GoodsSkuRepository skuRepository;
    private final GoodsSkuManager skuManager;
    private final GoodsSkuAttrRepository skuAttrRepository;
    private final GoodsSkuAttrManager skuAttrManager;
    private final GoodsSkuPackRepository skuPackRepository;
    private final GoodsSkuPackManager skuPackManager;

    private final SnowFlakeId snowFlakeId;


    /**
     * 更新商品信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void editGoodsAndSku(UpdateGoodsAndSkuParam param){
        // 更新商品
        UpdateGoodsParam updateGoodsParam = param.getGoodsParam();
        Goods oldGoods = goodsFactory.construct(updateGoodsParam);
        Goods goods = goodsManager.findById(oldGoods.getId())
                .orElseThrow(() -> new BizException("商品不存在"));
        BeanUtil.copyProperties(oldGoods,goods, CopyOptions.create().ignoreNullValue());
        goodsRepository.save(goods);

        // 删除
        List<Long> deleteIds = param.getDeleteIds();
        goodsManager.deleteByIds(deleteIds);
        skuAttrManager.deleteBySkuIds(deleteIds);

        // 新增
        List<CreateSkuParam> createSkuParams = param.getCreateSkuParams();
        skuService.addSkus(createSkuParams,goods);

        // 更新sku
        this.updateSkus(param.getUpdateSkuParams());
    }

    /**
     * 更新sku
     */
    private List<GoodsSku> updateSkus(List<UpdateSkuParam> updateSkuParams){
        List<GoodsSku> oldSkus = updateSkuParams.stream()
                .map(goodsSkuFactory::construct)
                .collect(Collectors.toList());
        List<Long> skuIds = oldSkus.stream().map(GoodsSku::getId).collect(Collectors.toList());
        List<GoodsSku> newSkus = skuManager.findByIds(skuIds);
        Map<Long, GoodsSku> skuMap = oldSkus.stream().collect(Collectors.toMap(GoodsSku::getId, o -> o));
        // 更新sku信息
        for (GoodsSku goodsSku : newSkus) {
            BeanUtil.copyProperties(skuMap.get(goodsSku.getId()),goodsSku);
        }

        List<GoodsSku> goodsSkus = skuRepository.saveAll(newSkus);

        // 更新关联属性 先删后增
        skuAttrManager.deleteBySkuIds(skuIds);
        List<GoodsSkuAttr> goodsSkuAttrs = AttrGenHandler.genSkuAttrsBySkus(newSkus);
        skuAttrRepository.saveAll(goodsSkuAttrs);
        return goodsSkus;
    }

    /**
     * 更新打包商品信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void editPackGoodsAndSku(UpdateGoodsAndSkuParam param){
        // 更新商品
        UpdateGoodsParam updateGoodsParam = param.getGoodsParam();
        Goods oldGoods = goodsFactory.construct(updateGoodsParam);
        Goods goods = goodsManager.findById(oldGoods.getId())
                .orElseThrow(() -> new BizException("商品不存在"));
        BeanUtil.copyProperties(oldGoods,goods, CopyOptions.create().ignoreNullValue());
        // 删除
        List<Long> deleteIds = param.getDeleteIds();
        goodsManager.deleteByIds(deleteIds);

        // 新增
        skuPackService.addPackSkus(param.getCreateSkuParams(), goods);

        // 更新
        skuPackService.updatePackSkus(param.getUpdateSkuParams());

        // 更新价格
        this.updatePrice(goods);
        goodsRepository.save(goods);
    }

    /**
     * 更新商品价格
     */
    private void updatePrice(Goods goods){
        BigDecimal upperPrice = goodsManager.getGoodsUpperPrice(goods.getId());
        BigDecimal lowerPrice = goodsManager.getGoodsLowerPrice(goods.getId());
        goods.setDisplayUpperPrice(upperPrice);
        goods.setDisplayLowerPrice(lowerPrice);
    }
}
