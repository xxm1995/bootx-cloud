package cn.bootx.paymentcenter.code.pay;

/**   
* 交易类型(名目)
* @author xxm  
* @date 2021/2/24 
*/
public interface PayTransactionTypeCode {

    /**
     * 交易支付类型名目 1.商品 2.补差价 3.退款 4.手续费 5.税款 6.额外费用 7.服务费用
     */
    int GOODS = 1;
    int DIFFERENCE = 2;
    int REFUND = 3;
    int FEES = 4;
    int FAXES = 5;
    int EXTRA = 6;
    int SERVICE = 7;
}
