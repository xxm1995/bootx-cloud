package cn.bootx.engine.shop.core.follow.dao;

import cn.bootx.common.headerholder.HeaderHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
*
* @author xxm
* @date 2021/2/18
*/
@Repository
@RequiredArgsConstructor
public class FollowManager {
    private final FollowRepository followRepository;
    private final HeaderHolder headerHolder;
}
