package cn.bootx.engine.shop.core.footmark.dao;

import cn.bootx.engine.shop.core.footmark.entity.FootMark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FootMarkRepository extends JpaRepository<FootMark,Long> {
    List<FootMark> findByUserIdAndTidOrderByLookTimeDesc(Long userId, Long tid);
}
