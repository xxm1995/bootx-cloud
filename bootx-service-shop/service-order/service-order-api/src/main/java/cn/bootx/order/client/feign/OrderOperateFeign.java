package cn.bootx.order.client.feign;

import cn.bootx.common.core.rest.ResResult;
import cn.bootx.order.code.OrderCenterCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = OrderCenterCode.APPLICATION_NAME,contextId = "orderOperateFeign",path = "/order")
public interface OrderOperateFeign {

    @ApiOperation("付款成功状态变更")
    @PostMapping("/paidOrderState")
    ResResult<Void> paidOrderState(@RequestParam Long orderId);

    @ApiOperation("取消订单")
    @PostMapping("/cancelOrderState")
    ResResult<Void> cancelOrderState(@RequestParam Long orderId);
}
