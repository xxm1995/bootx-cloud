package cn.bootx.paymentcenter.core.payconfig.dao;

import cn.bootx.paymentcenter.core.payconfig.entity.PayChannel;
import cn.bootx.starter.headerholder.HeaderHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**   
* 支付通道
* @author xxm  
* @date 2021/6/30 
*/
@Slf4j
@Repository
@RequiredArgsConstructor
public class PayChannelManager {
    private final PayChannelRepository repository;
    private final HeaderHolder headerHolder;

    public boolean existsByCode(String code) {
        return repository.existsByCodeAndTid(code,headerHolder.findTid());
    }

    public Optional<PayChannel> findById(Long id) {
        return repository.findByIdAndTid(id,headerHolder.findTid());

    }

    public Optional<PayChannel> findByCode(String code) {
        return repository.findByCodeAndTid(code,headerHolder.findTid());
    }

    public List<PayChannel> findAll() {
        return repository.findAllByTid(headerHolder.findTid());
    }
}
