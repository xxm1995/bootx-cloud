package cn.bootx.ordercenter.controller;

import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.ordercenter.core.order.service.OrderOperateService;
import cn.bootx.ordercenter.dto.order.OrderDto;
import cn.bootx.ordercenter.param.order.OrderWholeParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
*
* @author xxm
* @date 2021/4/13
*/
@Api(tags = "订单操作")
@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderOperateController {
    private final OrderOperateService orderOperateService;

    @ApiOperation("传入订单和优惠, 下单")
    @PostMapping("/placeOrder")
    public ResResult<OrderDto> placeOrder(@RequestBody OrderWholeParam orderWholeParam){
        return Res.ok(orderOperateService.placeOrder(orderWholeParam));
    }

    @ApiOperation("付款成功状态变更")
    @PostMapping("/paidOrderState")
    public ResResult<Void> paidOrderState(Long orderId){
        orderOperateService.paidOrderState(orderId);
        return Res.ok();
    }

    @ApiOperation("取消订单")
    @PostMapping("/cancelOrderState")
    public ResResult<Void> cancelOrderState(Long orderId) {
        orderOperateService.cancelOrderState(orderId);
        return Res.ok();
    }
}
