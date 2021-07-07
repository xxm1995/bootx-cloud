package cn.bootx.goodscenter.core.goods.service;

/**   
* 商品操作
* @author xxm  
* @date 2021/2/23 
*/

import cn.bootx.common.web.exception.BizException;
import cn.bootx.goodscenter.core.goods.dao.GoodsManager;
import cn.bootx.goodscenter.core.goods.dao.GoodsRepository;
import cn.bootx.goodscenter.core.goods.entity.Goods;
import cn.bootx.goodscenter.core.sku.dao.GoodsSkuManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 创建商品Service
 * @author xxm
 * @date 2021/2/2
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OperateGoodsService {

    private final GoodsRepository goodsRepository;
    private final GoodsManager goodsManager;
    private final GoodsSkuManager skuManager;

    /**
     * 上架商品
     */
    @Transactional(rollbackFor = Exception.class)
    public void saleOn(Long goodsId){
        Goods goods = goodsManager.findById(goodsId).orElseThrow(() -> new BizException("商品不存在"));
        goods.setSaleState(1)
                .setSaleOnTime(LocalDateTime.now());
        goodsRepository.save(goods);
        // 更新sku状态
        skuManager.saleOnByGoods(goodsId);
    }

    /**
     * 下架商品
     */
    @Transactional(rollbackFor = Exception.class)
    public void saleOff(Long goodsId){
        Goods goods = goodsManager.findById(goodsId).orElseThrow(() -> new BizException("商品不存在"));
        goods.setSaleState(0)
                .setSaleOffTime(LocalDateTime.now());
        goodsRepository.save(goods);
        // 更新sku状态
        skuManager.saleOffByGoods(goodsId);
    }

}
