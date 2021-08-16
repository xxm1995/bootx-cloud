package cn.bootx.goodscenter.client.feign;

import cn.bootx.common.core.rest.ResResult;
import cn.bootx.goodscenter.code.GoodsCenterCode;
import cn.bootx.goodscenter.dto.sku.GoodsSkuDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = GoodsCenterCode.APPLICATION_NAME,contextId = "goodsSkuFeign",path = "/sku")
interface GoodsSkuFeign {

    @ApiOperation("获取sku")
    @GetMapping("/findById")
    ResResult<GoodsSkuDto> findById(@RequestParam Long id);

    @ApiOperation("批量获取sku")
    @GetMapping("/findBySkuIds")
    ResResult<List<GoodsSkuDto>> findBySkuIds(@RequestParam List<Long> skuIds);

    @ApiOperation("根据goodsId查询")
    @GetMapping("/findByGoodsId")
    ResResult<List<GoodsSkuDto>> findByGoodsId(@RequestParam Long goodsId);

}
