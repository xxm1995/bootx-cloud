package cn.bootx.bsp.core.channel.dao;

import cn.bootx.bsp.core.channel.entity.Channel;
import cn.bootx.common.mybatisplus.base.MpBaseEntity;
import cn.bootx.common.mybatisplus.impl.BaseManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
* @author xxm
* @date 2020/10/31
*/
@Repository
@AllArgsConstructor
public class ChannelManager extends BaseManager<ChannelMapper,Channel> {


    public List<Channel> findAllByType(int type) {
        return findAllByField(Channel::getType,type);
    }

    public Optional<Channel> findByName(String name) {
        return findByField(Channel::getName,name);

    }

    /**
     * 渠道是否存在
     */
    public boolean existsByKey(String key){
        return existedByField(Channel::getType,key);
    }

    public boolean existsByKey(String key, Long id) {
        return lambdaQuery().eq(Channel::getKey,key)
                .ne(MpBaseEntity::getId,id)
                .exists();
    }

    public List<Channel> findKey(String key) {
        return findAllByField(Channel::getType,key);
    }
}
