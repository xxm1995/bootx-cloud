package cn.bootx.paymentcenter.core.merchant.dao;

import cn.bootx.paymentcenter.core.merchant.entity.MerchantInfo;
import cn.bootx.starter.headerholder.HeaderHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
* 商户
* @author xxm
* @date 2021/6/29
*/
@Slf4j
@Repository
@RequiredArgsConstructor
public class MerchantInfoManager {
    private final MerchantInfoRepository repository;
    private final HeaderHolder headerHolder;

    /**
     * 查询全部商户
     */
    public List<MerchantInfo> findAll() {
        return repository.findAllByTid(headerHolder.findTid());
    }

    /**
     * 获取单条
     */
    public Optional<MerchantInfo> findById(Long id) {
        return repository.findByIdAndTid(id,headerHolder.findTid());
    }
    /**
     * 获取单条
     */
    @Cacheable(value = "pc:merchant",key = "#merchantNo")
    public Optional<MerchantInfo> findByMerchantNo(String merchantNo) {
        return repository.findByMerchantNoAndTid(merchantNo,headerHolder.findTid());
    }

}
