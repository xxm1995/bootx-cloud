package cn.bootx.goodscenter.client.feign;

import cn.bootx.common.web.rest.ResResult;
import cn.bootx.goodscenter.code.GoodsCenterCode;
import cn.bootx.goodscenter.dto.sku.GoodsSkuDto;
import cn.bootx.goodscenter.param.sku.CreateSkuParam;
import cn.bootx.goodscenter.param.sku.GenSkuParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = GoodsCenterCode.APPLICATION_NAME,contextId = "goodsSkuFeign",path = "/sku")
interface GoodsSkuFeign {

    @ApiOperation("根据商品 SKU 属性生成其 SKU 预览")
    @PostMapping("/genSku")
    ResResult<List<GoodsSkuDto>> genSku(@RequestBody GenSkuParam genSkuParam);

    @ApiOperation("获取sku")
    @GetMapping("/get")
    ResResult<GoodsSkuDto> getById(@RequestParam Long id);

    @ApiOperation("获取sku")
    @GetMapping("/findBySkuIds")
    ResResult<List<GoodsSkuDto>> findBySkuIds(@RequestParam List<Long> skuIds);

    @ApiOperation("根据goodsId查询")
    @GetMapping("/findByGoodsId")
    ResResult<List<GoodsSkuDto>> findByGoodsId(@RequestParam Long goodsId);

    @ApiOperation("添加商品sku")
    @PostMapping("/add")
    ResResult<GoodsSkuDto> add(@RequestBody CreateSkuParam skuParam);
}
