package cn.bootx.sales.client.feign;

import cn.bootx.common.core.rest.ResResult;
import cn.bootx.sales.code.SalesCenterCode;
import cn.bootx.sales.dto.coupon.CouponDto;
import cn.bootx.sales.param.coupon.ObtainCouponParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = SalesCenterCode.APPLICATION_NAME,contextId = "couponFeign",path = "/coupon")
public interface CouponFeign {

    @ApiOperation("领取优惠券")
    @PostMapping("/obtainCoupon")
    ResResult<Void> obtainCoupon(@RequestBody ObtainCouponParam param);

    @ApiOperation("个人可用的优惠券")
    @GetMapping("/findByUser")
    ResResult<List<CouponDto>> findByUser(@RequestParam Long userId);

    @ApiOperation("批量查询优惠券")
    @GetMapping("/findByIds")
    ResResult<List<CouponDto>> findByIds(@RequestParam List<Long> couponIds);

    @ApiOperation("查询优惠券")
    @GetMapping("/getById")
    ResResult<CouponDto> getById(@RequestParam Long couponId);

    @ApiOperation("锁定优惠券")
    @PostMapping("/lockById")
    ResResult<Void> lockById(@RequestParam Long couponId);

    @ApiOperation("批量锁定优惠券")
    @PostMapping("/lockByIds")
    ResResult<Void> lockByIds(@RequestParam List<Long> couponIds);
}
