package cn.bootx.bsp.core.channel.dao;


import cn.bootx.bsp.core.channel.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ChannelRepository extends JpaRepository<Channel,Long> {

    List<Channel> findAllByTypeAndTid(int type, Long tid);

    List<Channel> findAllByIdInAndTid(Collection<Long> ids, Long tid);

    Optional<Channel> findByTidAndTypeAndClientId(Long tid, Integer type, Long clientId);

    boolean existsByKeyAndTid(String key, Long tid);

    void deleteByIdAndTid(Long id, Long tid);

    Optional<Channel> findByIdAndTid(Long id, Long tid);

    boolean existsByKeyAndIdNotAndTid(String key, Long id, Long tid);

    List<Channel> findAllByTid(Long tid);

    List<Channel> findAllByKeyAndTid(String key, Long tid);

    Optional<Channel> findByNameAndTid(String name, Long tid);
}
