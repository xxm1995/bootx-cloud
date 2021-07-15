package cn.bootx.engine.shop.controller;

import cn.bootx.common.web.rest.Res;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.engine.shop.core.pay.service.OrderAggregatePayService;
import cn.bootx.engine.shop.core.pay.service.OrderPayService;
import cn.bootx.engine.shop.core.order.service.OrderPlaceService;
import cn.bootx.engine.shop.param.sell.NowPlaceOrderParam;
import cn.bootx.engine.shop.param.sell.OrderAggregatePayParam;
import cn.bootx.engine.shop.param.sell.OrderPayParam;
import cn.bootx.engine.shop.param.sell.PlaceOrderParam;
import cn.bootx.ordercenter.dto.order.OrderDto;
import cn.bootx.paymentcenter.dto.pay.PayResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 *  订单控制器
 * @author xxm
 * @date 2021/2/17
 */
@Api(tags = "订单")
@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {
    private final OrderPayService payOrderService;
    private final OrderPlaceService placeOrderService;
    private final OrderAggregatePayService orderAggregatePayService;

    @ApiOperation("购物车下单")
    @PostMapping("/createOrderByCart")
    public ResResult<OrderDto> createOrderByCart(@RequestBody PlaceOrderParam param){
        return Res.ok(placeOrderService.createOrderByCart(param));
    }

    @ApiOperation("立即购买下单")
    @PostMapping("/buyNow")
    public ResResult<OrderDto> buyNow(@RequestBody NowPlaceOrderParam param){
        return Res.ok(placeOrderService.buyNow(param));
    }

    @ApiOperation("支付")
    @PostMapping("/pay")
    public ResResult<PayResult> payOrder(@RequestBody OrderPayParam param){
        return Res.ok(payOrderService.payOrder(param));
    }

    @ApiOperation("创建聚合支付")
    @PostMapping("/createAggregatePay")
    public ResResult<PayResult> createAggregatePay(@RequestBody OrderAggregatePayParam param){
        return Res.ok(orderAggregatePayService.createAggregatePay(param));
    }

    @SneakyThrows
    @ApiOperation("扫码聚合支付")
    @GetMapping("/aggregatePay")
    public void aggregatePay(String key, HttpServletRequest request, HttpServletResponse response){
        String ua = request.getHeader("User-Agent");
        String payBody = orderAggregatePayService.aggregatePay(key, ua);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(payBody);
        out.flush();
        out.close();
    }

    @ApiOperation("付款码聚合支付")
    @PostMapping("/aggregatePayBarCode")
    public ResResult<PayResult> aggregatePayBarCode(@RequestBody OrderAggregatePayParam param){
        return Res.ok(orderAggregatePayService.aggregatePayBarCode(param));
    }
}
