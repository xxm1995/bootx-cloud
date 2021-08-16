package cn.bootx.engine.shop.core.cart.entity;

import cn.bootx.common.core.exception.BizException;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 单个商品item元素
* @author xxm
* @date 2021/1/4
*/
@Data
@Accessors(chain = true)
public class ShopCartItem {

    /** skuId */
    private Long skuId;
    /** goodsId */
    private Long goodsId;
    /** 类目 */
    private Long categoryId;
    /** 商店id */
    private Long shopId;
    /** 订单号 比如：再来一单、定金预售等 */
    private Long orderId;
    /** 价格 */
    private BigDecimal goodsPrice;

    /** 数量 */
    private int num;
    /** 名称 */
    private String name;
    /** 商品名称 */
    private String goodsTitle;
    /** 排序 */
    private double order;
    /** 是否选中 */
    private boolean selected;
    /** 状态 无库存/库存不足/下架 */
    private int status;
    /** 选择的优惠策略 */
    private List<ItemActivity> activities;

    /** 总金额(原始) */
    private BigDecimal totalAmount;
    /** 优惠差价 */
    private BigDecimal payChange;
    /** 总金额(优惠后) */
    private BigDecimal payAmount;

    /** 附加参数 */
    private Map<String,Object> addition =new HashMap<>();

    /** 添加时间 */
    private LocalDateTime addTime;
    /** 更新时间 */
    private LocalDateTime updateTime;
    /**
     * 减少数量
     */
    public void lower(int num){
        if (num>this.num){
            throw new BizException("减少数超出现有数量");
        }
        this.num = this.getNum()-num;
    }
}
