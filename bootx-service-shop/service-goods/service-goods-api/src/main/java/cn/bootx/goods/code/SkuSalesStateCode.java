package cn.bootx.goods.code;

/**
* sku销售状态
* @author xxm
* @date 2021/2/22
*/
public interface SkuSalesStateCode {

    /** 下架(随商品状态) */
    int OFF_GOODS = 0;

    /** 上架 */
    int ON = 1;

    /** 下架(自身状态) */
    int OFF_SKU = 2;
}
