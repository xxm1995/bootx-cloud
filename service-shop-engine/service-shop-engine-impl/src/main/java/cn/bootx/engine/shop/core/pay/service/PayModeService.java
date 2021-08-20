package cn.bootx.engine.shop.core.pay.service;

import cn.bootx.engine.shop.param.sell.OrderPayParam;
import cn.bootx.order.dto.order.OrderDto;
import cn.bootx.paymentcenter.param.pay.PayParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
* 支付方式
* @author xxm
* @date 2021/3/17
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class PayModeService {
    /**
     * 构建支付中心所需要的支付参数
     */
    public PayParam buildPaymentParam(OrderDto orderDto, OrderPayParam param) {
        // 查询支付所需要的数据
        PayParam payParam = new PayParam();
        payParam.setBusinessId(String.valueOf(orderDto.getId()))
                .setUserId(orderDto.getUserId())
                .setTitle(orderDto.getDescription())
                .setPayModeList(param.getPayModeList());
        return payParam;
    }
}
