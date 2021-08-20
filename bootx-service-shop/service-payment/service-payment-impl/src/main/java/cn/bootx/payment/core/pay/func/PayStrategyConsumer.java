package cn.bootx.payment.core.pay.func;


import cn.bootx.payment.core.payment.entity.Payment;

import java.util.List;

/**
 * 支付策略接口
 * @author xxm
 * @date 2020/12/9
 */
@FunctionalInterface
public interface PayStrategyConsumer<T extends List<AbsPayStrategy>, S extends Payment> {
    void accept(T t, S s);
}
