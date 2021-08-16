package cn.bootx.paymentcenter.core.paymodel.alipay.dao;

import cn.bootx.common.core.rest.param.PageParam;
import cn.bootx.paymentcenter.core.paymodel.alipay.entity.AlipayConfig;
import cn.bootx.paymentcenter.core.paymodel.alipay.entity.QAlipayConfig;
import cn.bootx.paymentcenter.param.paymodel.alipay.AlipayConfigQuery;
import cn.bootx.starter.headerholder.HeaderHolder;
import cn.bootx.starter.jpa.utils.JpaUtils;
import cn.hutool.core.util.StrUtil;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
* 阿里支付配置
* @author xxm
* @date 2021/2/26
*/
@Repository
@RequiredArgsConstructor
public class AlipayConfigManager {
    private final AlipayConfigRepository repository;
    private final JPAQueryFactory jpaQueryFactory;
    private final HeaderHolder headerHolder;

    public Optional<AlipayConfig> findById(Long id){
        return repository.findByIdAndTid(id,headerHolder.findTid());
    }

    /**
     * 根据商户应用AppId获取支付宝支付配置
     */
    @Cacheable(value = "pc:alipay:config:appid",key = "#appId")
    public Optional<AlipayConfig> findByAppId(String appId){
        return repository.findByAppIdAndTid(appId,headerHolder.findTid());
    }

    public Page<AlipayConfig> page(PageParam pageParam, AlipayConfigQuery param) {
        QAlipayConfig q = QAlipayConfig.alipayConfig;
        JPAQuery<AlipayConfig> query = jpaQueryFactory.selectFrom(q);

        if (StrUtil.isNotBlank(param.getMerchantNo())){
            query.where(q.merchantNo.like("%"+param.getMerchantNo()+"%"));
        }
        if (StrUtil.isNotBlank(param.getAppId())){
            query.where(q.appId.like("%"+param.getAppId()+"%"));
        }
        if (StrUtil.isNotBlank(param.getName())){
            query.where(q.name.like("%"+param.getName()+"%"));
        }

        query.where(q.tid.eq(headerHolder.findTid()));
        return JpaUtils.queryPage(query,pageParam);
    }

    /**
     * 查询全部
     */
    public List<AlipayConfig> findAll() {
        return repository.findAllByTid(headerHolder.findTid());
    }

    public boolean existsByAppId(String appId) {
        return repository.existsByAppIdAndTid(appId,headerHolder.findTid());
    }

    public void deleteByAppId(String appId) {
        repository.deleteByAppIdAndTid(appId,headerHolder.findTid());
    }

}
