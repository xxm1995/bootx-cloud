package cn.bootx.salescenter.client.feign;

import cn.bootx.common.web.rest.ResResult;
import cn.bootx.salescenter.code.SalesCenterCode;
import cn.bootx.salescenter.dto.coupon.CouponDto;
import cn.bootx.salescenter.dto.order.GoodsActivityResult;
import cn.bootx.salescenter.dto.order.OrderPreviewResult;
import cn.bootx.salescenter.param.order.OrderCheckParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = SalesCenterCode.APPLICATION_NAME,contextId = "orderPreviewFeign",path = "/order/preview")
public interface OrderPreviewFeign {

    @ApiOperation("查询适用的活动")
    @PostMapping("/findActivityByOrder")
    ResResult<List<GoodsActivityResult>> findActivityByOrder(@RequestBody OrderCheckParam orderParam);

    @ApiOperation("查询适用的优惠券")
    @PostMapping("/findCouponByOrder")
    ResResult<List<CouponDto>> findCouponByOrder(@RequestBody OrderCheckParam orderParam);

    @ApiOperation("预览价格(手动)")
    @PostMapping("/previewOrderPrice")
    ResResult<OrderPreviewResult> previewOrderPrice(@RequestBody OrderCheckParam orderParam);

    @ApiOperation("预览价格(手动无检查)")
    @PostMapping("/previewOrderPriceNoCheck")
    ResResult<OrderPreviewResult> previewOrderPriceNoCheck(@RequestBody OrderCheckParam orderParam);

    @ApiOperation("预览价格(自动)")
    @PostMapping("/previewOrderPriceByAuto")
    ResResult<OrderPreviewResult> previewOrderPriceByAuto(@RequestBody OrderCheckParam orderParam);
}
