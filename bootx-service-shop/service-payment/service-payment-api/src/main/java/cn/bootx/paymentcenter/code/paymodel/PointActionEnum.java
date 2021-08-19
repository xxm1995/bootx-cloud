package cn.bootx.paymentcenter.code.paymodel;

import lombok.Getter;

/**
* 积分类型枚举
* @author xxm  
* @date 2020/12/11 
*/
@Getter
public enum PointActionEnum {

    /**
     * 购物奖励
     */
    TYPE_PURCHASE_REWARD("购物奖励", 1, "购物奖励"),

    /**
     * 后台奖励
     */
    TYPE_ADMIN_REWARD("系统发放", 2, "系统发放"),

    /**
     * 注册登录奖励
     */
    TYPE_REGISTER_REWARD("注册登录奖励", 3, "注册登录奖励"),

    /**
     * 退货返还
     */
    TYPE_REFUND_RESTORE("退货返还", 4, "退货返还"),

    /**
     * 购物消费
     */
    TYPE_PURCHASE_CONSUME("购物消费", 5, "购物消费"),

    /**
     * 系统收回
     */
    TYPE_REVOKE("系统收回", 8, "系统收回");

    private final String name;

    private final String description;

    private final int index;

    PointActionEnum(String name, int index, String description) {

        this.name = name;
        this.index = index;
        this.description = description;
    }

    public static PointActionEnum getByIndex(int index) {
        for (PointActionEnum pgt : PointActionEnum.values()) {
            if (index == pgt.getIndex()) {
                return pgt;
            }
        }
        return PointActionEnum.TYPE_ADMIN_REWARD;
    }
}
