package cn.bootx.ordercenter.client.feign;

import cn.bootx.common.web.rest.PageResult;
import cn.bootx.common.web.rest.ResResult;
import cn.bootx.common.web.rest.param.PageParam;
import cn.bootx.ordercenter.code.OrderCenterCode;
import cn.bootx.ordercenter.dto.order.OrderDto;
import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = OrderCenterCode.APPLICATION_NAME,contextId = "orderFindFeign",path = "/order")
public interface OrderFindFeign {

    @ApiOperation("根据用户获取订单")
    @GetMapping("/findByUser")
    ResResult<List<OrderDto>> findByUser(@RequestParam Long id);

    @ApiOperation("订单列表 分页")
    @PostMapping("/page")
    ResResult<PageResult<OrderDto>> page(@RequestBody PageParam page);

    @ApiOperation("获取完整订单详情")
    @GetMapping("/getWholeById")
    ResResult<OrderDto> getWholeById(@RequestParam Long id);

    @ApiOperation("查询订单包含的skuIds")
    @GetMapping("/findOrderSkuIds")
    ResResult<List<Long>> findOrderSkuIds(@RequestParam Long orderId);

    @ApiOperation("获取指定类型超时订单的id集合")
    @GetMapping("/findPayTimeoutOrderIdsByType")
    ResResult<List<Long>> findPayTimeoutOrderIdsByType(@DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN) @RequestParam LocalDateTime date, @RequestParam Integer type);
}
