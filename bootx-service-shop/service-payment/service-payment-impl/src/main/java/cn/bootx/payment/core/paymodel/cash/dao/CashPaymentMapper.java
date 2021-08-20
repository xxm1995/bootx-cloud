package cn.bootx.payment.core.paymodel.cash.dao;

import cn.bootx.payment.core.paymodel.cash.entity.CashPayment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**   
* 现金支付
* @author xxm  
* @date 2021/6/23 
*/
@Mapper
public interface CashPaymentMapper extends BaseMapper<CashPayment> {
}
