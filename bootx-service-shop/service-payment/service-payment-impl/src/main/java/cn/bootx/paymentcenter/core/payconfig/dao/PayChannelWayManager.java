package cn.bootx.paymentcenter.core.payconfig.dao;

import cn.bootx.common.core.rest.param.PageParam;
import cn.bootx.paymentcenter.core.payconfig.entity.PayChannelWay;
import cn.bootx.paymentcenter.core.payconfig.entity.QPayChannelWay;
import cn.bootx.paymentcenter.param.payconfig.PayChannelWayParam;
import cn.bootx.common.headerholder.HeaderHolder;
import cn.bootx.common.jpa.utils.JpaUtils;
import cn.hutool.core.util.StrUtil;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    private final JPAQueryFactory jpaQueryFactory;
    private final HeaderHolder headerHolder;

    public boolean existsByChannelAndCode(Long channelId, String code) {
        return repository.existsByChannelIdAndCodeAndTid(channelId,code,headerHolder.findTid());
    }

    public List<PayChannelWay> findByChannel(Long channelId) {
        return repository.findByChannelIdAndTid(channelId,headerHolder.findTid());
    }

    public Optional<PayChannelWay> findById(Long id) {
        return repository.findByIdAndTid(id,headerHolder.findTid());
    }

    public void deleteById(Long id) {
        repository.deleteByIdAndTid(id,headerHolder.findTid());
    }

    public Page<PayChannelWay> page(PageParam pageParam, PayChannelWayParam param) {
        QPayChannelWay q = QPayChannelWay.payChannelWay;
        JPAQuery<PayChannelWay> query = jpaQueryFactory.selectFrom(q);

        if (Objects.nonNull(param.getChannelId())){
            query.where(q.channelId.eq(param.getChannelId()));
        }
        if (StrUtil.isNotBlank(param.getCode())){
            query.where(q.code.likeIgnoreCase("%"+param.getCode()+"%"));
        }
        if (StrUtil.isNotBlank(param.getName())){
            query.where(q.name.like("%"+param.getName()+"%"));
        }
        query.where(q.tid.eq(headerHolder.findTid()));

        return JpaUtils.queryPage(query,pageParam);
    }

}
