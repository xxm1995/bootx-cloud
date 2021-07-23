package cn.bootx.engine.shop.controller;

import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.engine.shop.core.demo.service.DemoService;
import cn.bootx.engine.shop.param.demo.DemoPlaceAndPayParam;
import cn.bootx.goodscenter.dto.sku.GoodsSkuDto;
import cn.bootx.paymentcenter.dto.pay.PayResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**   
* @author xxm  
* @date 2021/7/23 
*/
@Api(tags = "支付结算演示demo")
@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {
    private final DemoService demoService;
    
    @ApiOperation("商品列表接口")
    @PostMapping("/findGoods")
    public ResResult<List<GoodsSkuDto>> findGoods(Long id){
        return Res.ok(demoService.findGoods(id));
    }


    @ApiOperation("下单和支付")
    @PostMapping("/placeAndPay")
    public ResResult<PayResult> placeAndPay(@RequestBody DemoPlaceAndPayParam param){
        return Res.ok(demoService.placeAndPay(param));
    }
}
