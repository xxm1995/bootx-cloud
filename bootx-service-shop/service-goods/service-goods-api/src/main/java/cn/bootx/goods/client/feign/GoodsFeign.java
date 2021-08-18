package cn.bootx.goods.client.feign;

import cn.bootx.common.core.rest.ResResult;
import cn.bootx.goods.code.GoodsCenterCode;
import cn.bootx.goods.dto.goods.GoodsDto;
import cn.bootx.goods.param.goods.GoodsParam;
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

    @ApiOperation("添加商品")
    @PostMapping("/add")
    ResResult<GoodsDto> add(@RequestBody GoodsParam param);

    @ApiOperation("查询全部")
    @GetMapping("/findAll")
    ResResult<List<GoodsDto>> findAll();

    @ApiOperation("按照类目查询")
    @GetMapping("/findByCategory")
    ResResult<List<GoodsDto>> findByCategory(@RequestParam Long cid);

    @ApiOperation("查询详情")
    @GetMapping("/getDetails")
    ResResult<GoodsDto> getDetails(@RequestParam Long id);

    @ApiOperation("查询包含商品信息")
    @GetMapping("/findById")
    ResResult<GoodsDto> findById(@RequestParam Long id);

}
