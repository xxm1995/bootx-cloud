package cn.bootx.goodscenter.core.packing.service;

import cn.bootx.common.web.util.ResultConvertUtils;
import cn.bootx.goodscenter.core.attributes.handler.AttrGenHandler;
import cn.bootx.goodscenter.core.goods.entity.Goods;
import cn.bootx.goodscenter.core.packing.dao.GoodsSkuPackManager;
import cn.bootx.goodscenter.core.packing.dao.GoodsSkuPackRepository;
import cn.bootx.goodscenter.core.packing.entity.GoodsSkuPack;
import cn.bootx.goodscenter.core.sku.dao.GoodsSkuAttrManager;
import cn.bootx.goodscenter.core.sku.dao.GoodsSkuAttrRepository;
import cn.bootx.goodscenter.core.sku.dao.GoodsSkuManager;
import cn.bootx.goodscenter.core.sku.dao.GoodsSkuRepository;
import cn.bootx.goodscenter.core.sku.entity.GoodsSku;
import cn.bootx.goodscenter.core.sku.entity.GoodsSkuAttr;
import cn.bootx.goodscenter.core.sku.factory.GoodsSkuFactory;
import cn.bootx.goodscenter.dto.packing.GoodsSkuPackingDto;
import cn.bootx.goodscenter.param.sku.CreateSkuParam;
import cn.bootx.goodscenter.param.sku.UpdateSkuParam;
import cn.bootx.starter.snowflake.SnowFlakeId;
import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**   
* 打包品(未开发完)
* @author xxm  
* @date 2020/11/26 
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class GoodsSkuPackService {
    private final GoodsSkuPackRepository skuPackRepository;
    private final GoodsSkuPackManager skuPackManager;
    private final GoodsSkuRepository skuRepository;
    private final GoodsSkuManager skuManager;
    private final GoodsSkuFactory skuFactory;
    private final GoodsSkuAttrRepository skuAttrRepository;
    private final GoodsSkuAttrManager skuAttrManager;

    private final SnowFlakeId snowFlakeId;

    /**
     * 构建打包品关系
     */
    public List<GoodsSkuPack> constructPackSku(GoodsSku sku, List<Long> packedSkuIds){
        return this.constructPackSku(sku.getGoodsId(),sku.getId(),packedSkuIds);
    }

    /**
     * 构建打包品关系
     */
    public List<GoodsSkuPack> constructPackSku(Long goodsId, Long skuId, List<Long> packedSkuIds){
        return packedSkuIds.stream()
                .map(packedSkuId-> new GoodsSkuPack()
                        .setGoodsId(goodsId)
                        .setGoodsSkuId(skuId)
                        .setPackedSkuId(packedSkuId)
                ).collect(Collectors.toList());
    }

    /**
     * 通过打包商品SKU id 获取其SKU关联被打包品信息
     */
    public List<GoodsSkuPackingDto> findBySkuId(Long skuId){
        return ResultConvertUtils.dtoListConvert(skuPackManager.findBySkuId(skuId));
    }

    /**
     * 新增打包sku
     */
    public List<GoodsSku> addPackSkus(List<CreateSkuParam> skuParams, Goods goods){
        List<GoodsSku> skus = new ArrayList<>(skuParams.size());
        List<GoodsSkuPack> skuPacks = new ArrayList<>();
        for (CreateSkuParam skuParam : skuParams) {
            long skuId = snowFlakeId.nextId();
            skuParam.setGoodsId(goods.getId())
                    .setPacking(true)
                    .setCid(goods.getCid());
            GoodsSku sku = skuFactory.construct(skuParam);
            sku.setId(skuId);
            skus.add(sku);
            List<GoodsSkuPack> goodsSkuPacks = this.constructPackSku(sku, skuParam.getPackedSkuIds());
            skuPacks.addAll(goodsSkuPacks);
        }
        // 保存sku和打包关系
        List<GoodsSku> goodsSkus = skuRepository.saveAll(skus);
        skuPackRepository.saveAll(skuPacks);
        return goodsSkus;
    }

    /**
     * 更新 打包商品
     */
    public void updatePackSkus(List<UpdateSkuParam> updateSkuParams){
        List<GoodsSku> oldSkus = updateSkuParams.stream()
                .map(skuFactory::construct)
                .collect(Collectors.toList());
        List<Long> skuIds = oldSkus.stream().map(GoodsSku::getId).collect(Collectors.toList());
        List<GoodsSku> newSkus = skuManager.findByIds(skuIds);
        Map<Long, GoodsSku> skuMap = oldSkus.stream().collect(Collectors.toMap(GoodsSku::getId, o -> o));

        // 更新sku信息
        for (GoodsSku goodsSku : newSkus) {
            BeanUtil.copyProperties(skuMap.get(goodsSku.getId()),goodsSku);
        }
        skuRepository.saveAll(newSkus);

        // 更新关联属性 先删后增
        skuAttrManager.deleteBySkuIds(skuIds);
        List<GoodsSkuAttr> goodsSkuAttrs = AttrGenHandler.genSkuAttrsBySkus(newSkus);
        skuAttrRepository.saveAll(goodsSkuAttrs);

        // 更新关联关系 先删后增
        List<GoodsSkuPack> skuPacks = new ArrayList<>();
        for (UpdateSkuParam skuParam : updateSkuParams) {
            List<GoodsSkuPack> goodsSkuPacks = this
                    .constructPackSku(skuParam.getGoodsId(),skuParam.getId(),skuParam.getPackedSkuIds());
            skuPacks.addAll(goodsSkuPacks);
        }
        skuPackManager.deleteBySkuIds(skuIds);
        skuPackRepository.saveAll(skuPacks);
    }


}
