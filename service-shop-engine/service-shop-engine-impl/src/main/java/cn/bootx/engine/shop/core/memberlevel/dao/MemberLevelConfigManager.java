package cn.bootx.engine.shop.core.memberlevel.dao;

import cn.bootx.common.headerholder.HeaderHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
*
* @author xxm
* @date 2021/2/22
*/
@Repository
@RequiredArgsConstructor
public class MemberLevelConfigManager {
    private final MemberLevelConfigRepository memberLevelConfigRepository;

    private final HeaderHolder headerHolder;
}
