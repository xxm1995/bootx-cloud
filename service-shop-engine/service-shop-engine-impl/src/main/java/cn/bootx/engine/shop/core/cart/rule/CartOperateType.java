package cn.bootx.engine.shop.core.cart.rule;

/**   
* 购物车操作类型
* @author xxm  
* @date 2021/2/17 
*/
public enum CartOperateType {
    /** 加入 */
    JOIN,
    /** 增加 */
    ADD,
    /** 减少 */
    REDUCE,
    /** 更改数量 */
    CHANGE_NUM,
    /** 更改规格 */
    CHANGE_SPECIFICATION;
}
