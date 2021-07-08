package cn.bootx.salescenter.client.feign;

import cn.bootx.salescenter.client.CouponClient;
import cn.bootx.salescenter.dto.coupon.CouponDto;
import cn.bootx.salescenter.param.coupon.ObtainCouponParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnMissingBean(CouponClient.class)
@RequiredArgsConstructor
public class CouponClientFeignImpl implements CouponClient {
    @Autowired(required = false)
    private CouponFeign couponFeign;

    @Override
    public void obtainCoupon(ObtainCouponParam param) {
        couponFeign.obtainCoupon(param);
    }

    @Override
    public List<CouponDto> findByUser(Long userId) {
        return couponFeign.findByUser(userId).getData();
    }

    @Override
    public List<CouponDto> findByIds(List<Long> ids) {
        return couponFeign.findByIds(ids).getData();
    }

    @Override
    public CouponDto getById(Long id) {
        return couponFeign.getById(id).getData();
    }

    @Override
    public void lockByIds(List<Long> ids) {
        couponFeign.lockByIds(ids);
    }

    @Override
    public void lockById(Long id) {
        couponFeign.lockById(id);
    }
}
