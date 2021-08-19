package cn.bootx.paymentcenter.core.merchant.dao;

import cn.bootx.paymentcenter.core.merchant.entity.MerchantApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**   
* 商户APP
* @author xxm  
* @date 2021/6/29 
*/
public interface MerchantAppRepository extends JpaRepository<MerchantApp,Long> {
    List<MerchantApp> findAllByTid(Long tid);

    Optional<MerchantApp> findByIdAndTid(Long id, Long tid);

    boolean existsByIdAndTid(Long id, Long tid);

    List<MerchantApp> findByMerchantIdAndTid(Long merchantId, Long tid);

    boolean existsByMerchantNoAndAppIdAndTid(String merchantNo, String appId, Long tid);

    boolean existsByAppIdAndTid(String appId, Long tid);

    Optional<MerchantApp> findByMerchantNoAndAppIdAndTid(String merchantNo, String appId, Long tid);
}
