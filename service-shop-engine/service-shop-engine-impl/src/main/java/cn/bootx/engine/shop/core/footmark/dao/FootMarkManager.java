package cn.bootx.engine.shop.core.footmark.dao;

import cn.bootx.engine.shop.core.footmark.entity.FootMark;
import cn.bootx.starter.headerholder.HeaderHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**   
*
* @author xxm  
* @date 2021/2/22 
*/
@Repository
@RequiredArgsConstructor
public class FootMarkManager {
    private final FootMarkRepository footMarkRepository;

    private final HeaderHolder headerHolder;

    public List<FootMark> findByUser(Long userId) {
        return footMarkRepository.findByUserIdAndTidOrderByLookTimeDesc(userId,headerHolder.findTid());
    }
}
