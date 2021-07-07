package cn.bootx.goodscenter.event;

/**
 * 商品中心事件
 * @author xxm
 * @date 2021/4/22
 */
public interface GoodsEventCode {

    /** 商品库存操作交换机 */
    String EXCHANGE_GOODS = "service.goods.center";

    /** 解锁库存 收 */
    String UNLOCK_INVENTORY = "inventory.unlock";

    /** 扣库存 收 */
    String REDUCE_INVENTORY = "inventory.reduce";

}
