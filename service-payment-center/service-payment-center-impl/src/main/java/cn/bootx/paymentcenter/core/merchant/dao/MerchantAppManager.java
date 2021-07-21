package cn.bootx.paymentcenter.core.merchant.dao;

import cn.bootx.common.web.rest.param.PageParam;
import cn.bootx.paymentcenter.core.merchant.entity.MerchantApp;
import cn.bootx.paymentcenter.core.merchant.entity.QMerchantApp;
import cn.bootx.paymentcenter.param.merchant.MerchantAppParam;
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
* 商户APP
* @author xxm  
* @date 2021/6/29 
*/
@Slf4j
@Repository
@RequiredArgsConstructor
public class MerchantAppManager {
    private final MerchantAppRepository repository;
    private final JPAQueryFactory jpaQueryFactory;
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

    public Page<MerchantApp> page(PageParam pageParam, MerchantAppParam param) {
        QMerchantApp q = QMerchantApp.merchantApp;
        JPAQuery<MerchantApp> query = jpaQueryFactory.selectFrom(q);

        if (StrUtil.isNotBlank(param.getMerchantNo())){
            query.where(q.merchantNo.like("%"+param.getMerchantNo()+"%"));
        }
        if (StrUtil.isNotBlank(param.getAppId())){
            query.where(q.appId.like("%"+param.getAppId()+"%"));
        }
        if (StrUtil.isNotBlank(param.getAppName())){
            query.where(q.appName.like("%"+param.getAppName()+"%"));
        }

        return JpaUtils.queryPage(query,pageParam);
    }
}
