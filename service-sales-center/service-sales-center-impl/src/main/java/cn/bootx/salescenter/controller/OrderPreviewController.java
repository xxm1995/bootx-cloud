package cn.bootx.salescenter.controller;

import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.salescenter.core.calculate.service.OrderPreviewService;
import cn.bootx.salescenter.dto.order.OrderPreviewResult;
import cn.bootx.salescenter.param.order.OrderCheckParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @author xxm
* @date 2020/10/17
*/
@Api(tags = "订单计算")
@RestController
@RequestMapping("/order/preview")
@AllArgsConstructor
public class OrderPreviewController {
    private final OrderPreviewService orderPreviewService;


    @ApiOperation("预览价格(手动)")
    @PostMapping("/previewOrderPrice")
    public ResResult<OrderPreviewResult> previewOrderPrice(@RequestBody OrderCheckParam orderParam){
        return Res.ok(orderPreviewService.previewOrderPrice(orderParam));
    }

    @ApiOperation("预览价格(手动无检查)")
    @PostMapping("/previewOrderPriceNoCheck")
    public ResResult<OrderPreviewResult> previewOrderPriceNoCheck(@RequestBody OrderCheckParam orderParam){
        return Res.ok(orderPreviewService.previewOrderPriceNoCheck(orderParam));
    }
}
