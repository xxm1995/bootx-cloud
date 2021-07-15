package cn.bootx.salescenter.event;

/**
 * 销售中心事件
 * @author xxm
 * @date 2021/4/29
 */
public interface SalesEventCode {
    /** 销售中心交换机 */
    String EXCHANGE_SALES = "service.sales.center";

    /** 使用优惠券事件 */
    String USE_COUPON = "coupon.use";

    /** 使用优惠券事件 */
    String UNLOCK_COUPON = "coupon.unlock";

}
