package cn.bootx.goodscenter.controller;

import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.goodscenter.core.goods.service.GoodsAddService;
import cn.bootx.goodscenter.core.goods.service.GoodsService;
import cn.bootx.goodscenter.dto.goods.GoodsDto;
import cn.bootx.goodscenter.dto.sku.GoodsSkuDto;
import cn.bootx.goodscenter.param.goods.CreateGoodsAndSkuParam;
import cn.bootx.goodscenter.param.sku.CreateSkuParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "商品管理")
@RestController
@RequestMapping("/goods")
@AllArgsConstructor
public class GoodsController{
    private final GoodsService goodsService;
    private final GoodsAddService createGoodsService;

    @ApiOperation("添加单品")
    @PostMapping("/addSingle")
    public ResResult<GoodsSkuDto> addSingle(@RequestBody CreateSkuParam param){
        return Res.ok(createGoodsService.addSingle(param));
    }

    @ApiOperation("添加商品")
    @PostMapping("/add")
    public ResResult<GoodsDto> add(@RequestBody CreateGoodsAndSkuParam param){
        return Res.ok(createGoodsService.addGoodsAndSku(param));
    }

    @ApiOperation("添加打包商品")
    @PostMapping("/addPackGoods")
    public ResResult<GoodsDto> addPackGoods(@RequestBody CreateGoodsAndSkuParam param){
        return Res.ok(createGoodsService.addPackGoodsAndSku(param));
    }

    @ApiOperation("查询全部")
    @GetMapping("/findAll")
    public ResResult<List<GoodsDto>> findAll(){
        return Res.ok(goodsService.findAll());
    }

    @ApiOperation("查询详情")
    @GetMapping("/getDetails")
    public ResResult<GoodsDto> getDetails(Long id){
        return Res.ok(goodsService.getDetails(id));
    }

    @ApiOperation("按类目查询")
    @GetMapping("/findByCategory")
    public ResResult<List<GoodsDto>> findByCategory(Long cid){
        return Res.ok(goodsService.findByCategory(cid));
    }
}
