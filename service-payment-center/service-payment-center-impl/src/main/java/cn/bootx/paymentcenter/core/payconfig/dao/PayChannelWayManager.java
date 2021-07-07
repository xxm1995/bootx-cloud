package cn.bootx.paymentcenter.core.payconfig.dao;

import cn.bootx.paymentcenter.core.payconfig.entity.PayChannelWay;
import cn.bootx.starter.headerholder.HeaderHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

/**   
* 支付通道支持的支付方式
* @author xxm  
* @date 2021/6/30 
*/
@Slf4j
@Repository
@RequiredArgsConstructor
public class PayChannelWayManager {
    private final PayChannelWayRepository repository;
    private final HeaderHolder headerHolder;

    public boolean existsByChannelAndCode(Long channelId, String code) {
        return repository.existsByChannelIdAndCodeAndTid(channelId,code,headerHolder.findTid());
    }

    public List<PayChannelWay> findByChannel(Long channelId) {
        return repository.findByChannelIdAndTid(channelId,headerHolder.findTid());
    }

    public void deleteById(Long id) {
        repository.deleteByIdAndTid(id,headerHolder.findTid());
    }
}
