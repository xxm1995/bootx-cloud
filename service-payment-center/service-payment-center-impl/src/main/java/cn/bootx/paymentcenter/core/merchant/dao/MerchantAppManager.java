package cn.bootx.paymentcenter.core.merchant.dao;

import cn.bootx.paymentcenter.core.merchant.entity.MerchantApp;
import cn.bootx.starter.headerholder.HeaderHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**   
* 商户APP
* @author xxm  
* @date 2021/6/29 
*/
@Slf4j
@Repository
@RequiredArgsConstructor
public class MerchantAppManager {
    private final MerchantAppRepository repository;
    private final HeaderHolder headerHolder;

    public List<MerchantApp> findAll() {
        return repository.findAllByTid(headerHolder.findTid());
    }

    public Optional<MerchantApp> findById(Long id) {
        return repository.findByIdAndTid(id,headerHolder.findTid());
    }

    @Cacheable(value = "pc:merchant:app",key = "#merchantNo+':'+#appId")
    public Optional<MerchantApp> findByMerchantNoAndAppId(String merchantNo,String appId) {
        return repository.findByMerchantNoAndAppIdAndTid(merchantNo,appId,headerHolder.findTid());
    }

    public boolean existsById(Long id){
        return repository.existsByIdAndTid(id,headerHolder.findTid());
    }

    public boolean existsByAppId(String appId){
        return repository.existsByAppIdAndTid(appId,headerHolder.findTid());
    }

    public List<MerchantApp> findByMerchantId(Long merchantId) {
        return repository.findByMerchantIdAndTid(merchantId,headerHolder.findTid());
    }

    public boolean existsByMerchantNoAndAppId(String merchantNo, String appId) {
        return repository.existsByMerchantNoAndAppIdAndTid(merchantNo,appId,headerHolder.findTid());
    }
}
