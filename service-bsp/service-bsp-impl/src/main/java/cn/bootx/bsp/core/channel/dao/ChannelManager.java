package cn.bootx.bsp.core.channel.dao;

import cn.bootx.bsp.core.channel.entity.Channel;
import cn.bootx.starter.headerholder.HeaderHolder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
* @author xxm
* @date 2020/10/31
*/
@Service
@AllArgsConstructor
public class ChannelManager {

    private final ChannelRepository channelRepository;
    private final HeaderHolder headerHolder;

    @Transactional(rollbackFor = Exception.class)
    public Channel update(Channel channel) {
        return channelRepository.saveAndFlush(channel);
    }


    public List<Channel> findAll() {
        return channelRepository.findAllByTid(headerHolder.findTid());
    }

    public List<Channel> findAllByType(int type) {
        return channelRepository.findAllByTypeAndTid(type,headerHolder.findTid());
    }

    public Optional<Channel> findByName(String name) {
        return channelRepository.findByNameAndTid(name,headerHolder.findTid());

    }

    public Optional<Channel> findById(Long id) {
        return channelRepository.findByIdAndTid(id,headerHolder.findTid());

    }

    public List<Channel> findAllByIds(Collection<Long> ids) {
        return channelRepository.findAllByIdInAndTid(ids,headerHolder.findTid());
    }

    /**
     * 渠道是否存在
     */
    public boolean existsByKey(String key){
        return channelRepository.existsByKeyAndTid(key,headerHolder.findTid());
    }

    public void deleteById(Long id) {
        channelRepository.deleteByIdAndTid(id,headerHolder.findTid());
    }

    public boolean existsByKey(String key, Long id) {
        return channelRepository.existsByKeyAndIdNotAndTid(key,id,headerHolder.findTid());
    }

    public List<Channel> findKey(String key) {
        return channelRepository.findAllByKeyAndTid(key,headerHolder.findTid());
    }
}
