package cn.bootx.engine.shop.core.memberlevel.dao;

import cn.bootx.engine.shop.core.memberlevel.entity.MemberLevelConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberLevelConfigRepository extends JpaRepository<MemberLevelConfig,Long> {
}
