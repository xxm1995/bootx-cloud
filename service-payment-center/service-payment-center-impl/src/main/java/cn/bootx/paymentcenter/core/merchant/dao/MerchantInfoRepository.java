package cn.bootx.paymentcenter.core.merchant.dao;

import cn.bootx.paymentcenter.core.merchant.entity.MerchantInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
* 商户
* @author xxm
* @date 2021/6/29
*/
public interface MerchantInfoRepository extends JpaRepository<MerchantInfo,Long> {

    List<MerchantInfo> findAllByTid(Long tid);

    Optional<MerchantInfo> findByIdAndTid(Long id, Long tid);

    boolean existsByIdAndTid(Long id, Long tid);

    Optional<MerchantInfo> findByMerchantNoAndTid(String merchantNo, Long tid);
}
