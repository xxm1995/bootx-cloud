package cn.bootx.paymentcenter.core.paymodel.wechat.dao;

import cn.bootx.paymentcenter.core.paymodel.wechat.entity.WeChatPayment;
import cn.bootx.common.headerholder.HeaderHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**   
* 微信支付记录
* @author xxm  
* @date 2021/6/21 
*/
@Slf4j
@Repository
@RequiredArgsConstructor
public class WeChatPaymentManager {
    private final WeChatPaymentRepository repository;
    private final HeaderHolder headerHolder;


    public Optional<WeChatPayment> findByPaymentId(Long paymentId) {
        return repository.findByPaymentIdAndTid(paymentId,headerHolder.getTid());
    }
}
