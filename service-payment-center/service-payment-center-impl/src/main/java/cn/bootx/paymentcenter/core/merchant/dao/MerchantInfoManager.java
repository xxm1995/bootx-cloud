package cn.bootx.paymentcenter.core.merchant.dao;

import cn.bootx.common.web.rest.param.PageParam;
import cn.bootx.paymentcenter.core.merchant.entity.MerchantInfo;
import cn.bootx.paymentcenter.core.merchant.entity.QMerchantInfo;
import cn.bootx.paymentcenter.param.merchant.MerchantInfoParam;
import cn.bootx.starter.headerholder.HeaderHolder;
import cn.bootx.starter.jpa.utils.JpaUtils;
import cn.hutool.core.util.StrUtil;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
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
    private final JPAQueryFactory jpaQueryFactory;
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

    public Page<MerchantInfo> page(PageParam pageParam, MerchantInfoParam param) {
        QMerchantInfo q = QMerchantInfo.merchantInfo;
        JPAQuery<MerchantInfo> query = jpaQueryFactory.selectFrom(q);

        if (StrUtil.isNotBlank(param.getMerchantNo())){
            query.where(q.merchantNo.like("%"+param.getMerchantNo()+"%"));
        }
        if (StrUtil.isNotBlank(param.getMerchantName())){
            query.where(q.merchantName.like("%"+param.getMerchantName()+"%"));
        }

        return JpaUtils.queryPage(query,pageParam);
    }
}
