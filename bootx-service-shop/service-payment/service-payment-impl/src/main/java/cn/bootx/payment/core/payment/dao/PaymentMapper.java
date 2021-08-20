package cn.bootx.payment.core.payment.dao;

import cn.bootx.payment.core.payment.entity.Payment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**   
* 支付记录
* @author xxm  
* @date 2021/7/27 
*/
@Mapper
public interface PaymentMapper extends BaseMapper<Payment> {


}
