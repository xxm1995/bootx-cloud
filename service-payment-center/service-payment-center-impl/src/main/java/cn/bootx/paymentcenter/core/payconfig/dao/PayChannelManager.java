package cn.bootx.paymentcenter.core.payconfig.dao;

import cn.bootx.common.core.rest.param.PageParam;
import cn.bootx.paymentcenter.core.payconfig.entity.PayChannel;
import cn.bootx.paymentcenter.core.payconfig.entity.QPayChannel;
import cn.bootx.paymentcenter.param.payconfig.PayChannelParam;
import cn.bootx.starter.headerholder.HeaderHolder;
import cn.bootx.starter.jpa.utils.JpaUtils;
import cn.hutool.core.util.StrUtil;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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
    private final JPAQueryFactory jpaQueryFactory;
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

    public Page<PayChannel> page(PageParam pageParam, PayChannelParam param) {
        QPayChannel q = QPayChannel.payChannel;
        JPAQuery<PayChannel> query = jpaQueryFactory.selectFrom(q);

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
