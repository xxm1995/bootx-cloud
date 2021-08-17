package cn.bootx.bsp.client.feign;

import cn.bootx.bsp.param.channel.ChannelParam;
import cn.bootx.bsp.client.ChannelClient;
import cn.bootx.bsp.dto.channel.ChannelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* 渠道
* @author xxm
* @date 2021/4/9
*/
@Component
@RequiredArgsConstructor
public class ChannelClientFeignImpl implements ChannelClient{
    @Autowired(required = false)
    private ChannelFeign channelFeign;

    @Override
    public ChannelDto add(ChannelParam param) {
        return channelFeign.add(param).getData();
    }

    @Override
    public void delete(Long id) {
        channelFeign.delete(id);
    }

    @Override
    public ChannelDto update(ChannelParam param) {
        return channelFeign.update(param).getData();
    }

    @Override
    public ChannelDto findById(Long id) {
        return channelFeign.findById(id).getData();
    }

    @Override
    public List<ChannelDto> findAll() {
        return channelFeign.findAll().getData();
    }

    @Override
    public List<ChannelDto> findByType(int type) {
        return channelFeign.findByType(type).getData();
    }

    @Override
    public List<ChannelDto> findByKey(String key) {
        return channelFeign.findByKey(key).getData();
    }

    @Override
    public ChannelDto findByName(String name) {
        return channelFeign.findByName(name).getData();
    }

    @Override
    public List<ChannelDto> findByIds(List<Long> ids) {
        return channelFeign.findByIds(ids).getData();
    }
}
