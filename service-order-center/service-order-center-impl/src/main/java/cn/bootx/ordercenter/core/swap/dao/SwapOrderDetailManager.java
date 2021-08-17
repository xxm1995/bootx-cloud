package cn.bootx.ordercenter.core.swap.dao;

import cn.bootx.common.headerholder.HeaderHolder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
* @author xxm
* @date 2020/11/19
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SwapOrderDetailManager {
    private final SwapOrderDetailRepository swapOrderDetailRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private final HeaderHolder headerHolder;
}
