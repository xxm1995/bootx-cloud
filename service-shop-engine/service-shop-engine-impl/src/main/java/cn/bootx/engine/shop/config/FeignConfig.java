package cn.bootx.engine.shop.config;

import cn.bootx.goods.client.feign.GoodsCenterFeign;
import cn.bootx.order.client.feign.OrderCenterFeign;
import cn.bootx.paymentcenter.client.feign.PaymentCenterFeign;
import cn.bootx.sales.client.feign.SalesCenterFeign;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackageClasses ={
        GoodsCenterFeign.class,
        PaymentCenterFeign.class,
        SalesCenterFeign.class,
        OrderCenterFeign.class
})
public class FeignConfig {
}
