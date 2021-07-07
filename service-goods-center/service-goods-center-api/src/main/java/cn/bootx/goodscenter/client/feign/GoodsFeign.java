package cn.bootx.goodscenter.client.feign;

import cn.bootx.common.web.rest.ResResult;
import cn.bootx.goodscenter.code.GoodsCenterCode;
import cn.bootx.goodscenter.dto.goods.GoodsDto;
import cn.bootx.goodscenter.dto.sku.GoodsSkuDto;
import cn.bootx.goodscenter.param.goods.CreateGoodsAndSkuParam;
import cn.bootx.goodscenter.param.sku.CreateSkuParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**   
* 商品接口
* @author xxm  
* @date 2021/3/21 
*/
@FeignClient(name = GoodsCenterCode.APPLICATION_NAME,contextId = "goodsFeign",path = "/goods")
public interface GoodsFeign {

    @ApiOperation("添加单品")
    @PostMapping("/addSingle")
    ResResult<GoodsSkuDto> addSingle(@RequestBody CreateSkuParam param);

    @ApiOperation("添加商品")
    @PostMapping("/add")
    ResResult<GoodsDto> add(@RequestBody CreateGoodsAndSkuParam param);

    @ApiOperation("添加打包商品")
    @PostMapping("/addPackGoods")
    ResResult<GoodsDto> addPackGoods(@RequestBody CreateGoodsAndSkuParam param);

    @ApiOperation("查询全部")
    @GetMapping("/findAll")
    ResResult<List<GoodsDto>> findAll();

    @ApiOperation("按照类目查询")
    @GetMapping("/findByCategory")
    ResResult<List<GoodsDto>> findByCategory(@RequestParam Long cid);

    @ApiOperation("查询详情")
    @GetMapping("/getDetails")
    ResResult<GoodsDto> getDetails(@RequestParam Long id);

}
