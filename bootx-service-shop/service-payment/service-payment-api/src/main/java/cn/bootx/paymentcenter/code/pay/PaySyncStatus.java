package cn.bootx.paymentcenter.code.pay;

/**
* 支付网关同步状态
* @author xxm
* @date 2021/4/21
*/
public interface PaySyncStatus {

    /** -1 不需要同步 */
    int NOT_SYNC = -1;
    /** 1 远程支付成功 */
    int TRADE_SUCCESS = 1;
    /** 2 交易创建，等待买家付款 */
    int WAIT_BUYER_PAY = 2;
    /** 3 已关闭 */
    int TRADE_CLOSED = 3;
    /** 4 查询不到订单 */
    int NOT_FOUND = 4;
    /** 5 查询失败 */
    int FAIL = 5;
}
