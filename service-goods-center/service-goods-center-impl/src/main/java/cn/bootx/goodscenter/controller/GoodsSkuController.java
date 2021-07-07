package cn.bootx.goodscenter.controller;

import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.goodscenter.core.sku.entity.GoodsSku;
import cn.bootx.goodscenter.core.sku.service.GoodsSkuGenService;
import cn.bootx.goodscenter.core.sku.service.GoodsSkuOperateService;
import cn.bootx.goodscenter.core.sku.service.GoodsSkuService;
import cn.bootx.goodscenter.dto.sku.GoodsSkuDto;
import cn.bootx.goodscenter.param.sku.CreateSkuParam;
import cn.bootx.goodscenter.param.sku.GenSkuParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @author xxm
* @date 2020/11/21
*/
@Api(tags = "sku操作")
@RestController
@RequestMapping("/sku")
@AllArgsConstructor
public class GoodsSkuController {
    private final GoodsSkuService goodsSkuService;
    private final GoodsSkuOperateService goodsSkuOperateService;
    private final GoodsSkuGenService goodsSkuGenService;

    @ApiOperation("根据商品 SKU 属性生成其 SKU 预览")
    @PostMapping("/genSku")
    public ResResult<List<GoodsSkuDto>> genSku(@RequestBody GenSkuParam genSkuParam){
        return Res.ok(goodsSkuGenService.genSkuByAttrs(genSkuParam));
    }

    @ApiOperation("获取sku")
    @GetMapping("/get")
    public ResResult<GoodsSkuDto> getById(Long id){
        return Res.ok(goodsSkuService.getById(id));
    }

    @ApiOperation("获取sku")
    @GetMapping("/findBySkuIds")
    public ResResult<List<GoodsSkuDto>> findBySkuIds(List<Long> skuIds){
        return Res.ok(goodsSkuService.findBySkuIds(skuIds));
    }

    @ApiOperation("根据goodsId查询")
    @GetMapping("/findByGoodsId")
    public ResResult<List<GoodsSkuDto>> findByGoodsId(Long goodsId){
        return Res.ok(goodsSkuService.findByGoodsId(goodsId));
    }

    @ApiOperation("添加商品sku")
    @PostMapping("/add")
    public ResResult<GoodsSkuDto> add(@RequestBody CreateSkuParam skuParam){
        return Res.ok(goodsSkuOperateService.add(skuParam));
    }

}
