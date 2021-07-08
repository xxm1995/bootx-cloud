package cn.bootx.ordercenter.core.swap.dao;

import cn.bootx.ordercenter.dto.swap.SwapOrderDto;
import cn.bootx.starter.headerholder.HeaderHolder;
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
public class SwapOrderManager {
    private final SwapOrderRepository swapOrderRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private final HeaderHolder headerHolder;

    /**
     * 申请换货单
     */
    public void addSwapOrder(SwapOrderDto dto){

    }


}
