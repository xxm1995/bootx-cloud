package cn.bootx.paymentcenter.code.pay;

/**   
* 交易目的
* @author xxm  
* @date 2021/2/24 
*/
public interface PayTransactionPurposeCode {

    /**
     * 支付目的 1.购买 2.换货 3.退货
     */
    int PURCHASE = 1;
    int EXCHANGE = 2;
    int RETURN = 3;
}
