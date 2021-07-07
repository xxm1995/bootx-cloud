package cn.bootx.goodscenter.controller;

import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.goodscenter.core.packing.service.GoodsSkuPackService;
import cn.bootx.goodscenter.dto.packing.GoodsSkuPackingDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* @author xxm
* @date 2020/11/23
*/
@Api(tags = "SKU打包")
@RestController
@RequestMapping("/pack/sku")
@AllArgsConstructor
public class GoodsSkuPackController {
    private final GoodsSkuPackService goodsSkuPackService;


    @ApiOperation("通过打包品SKU id 获取其关联的被打包品信息")
    @GetMapping("/findBySkuId")
    public ResResult<List<GoodsSkuPackingDto>> findBySkuId(Long skuId){
        return Res.ok(goodsSkuPackService.findBySkuId(skuId));
    }
}
