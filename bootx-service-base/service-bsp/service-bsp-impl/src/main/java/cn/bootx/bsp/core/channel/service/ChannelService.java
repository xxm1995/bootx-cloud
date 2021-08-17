package cn.bootx.bsp.core.channel.service;

import cn.bootx.bsp.core.channel.dao.ChannelManager;
import cn.bootx.bsp.core.channel.entity.Channel;
import cn.bootx.bsp.dto.channel.ChannelDto;
import cn.bootx.bsp.exception.channel.ChannelAlreadyExistsException;
import cn.bootx.bsp.param.channel.ChannelParam;
import cn.bootx.common.core.exception.BizException;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alibaba.csp.sentinel.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**   
* 渠道
* @author xxm  
* @date 2020/10/16 
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class ChannelService{
    private final ChannelManager channelManager;

    /**
     * 增加Channel
     */
    public ChannelDto add(ChannelParam param) {
        // 判断tid+key是否存在
        if(channelManager.existsByKey(param.getKey())){
            throw new ChannelAlreadyExistsException();
        }
        Channel channel = Channel.init(param);
        channel.setActive(true);
        return channelManager.save(channel).toDto();
    }

    /**
     * 删除渠道
     */
    public void delete(Long id) {
        channelManager.deleteById(id);
    }

    /**
     * 修改渠道数据
     */
    public ChannelDto update(ChannelParam param){
        Channel channel = channelManager.findById(param.getId()).orElseThrow(BizException::new);
        // 判断tid+key是否存在
        if(channelManager.existsByKey(param.getKey(),channel.getId())){
            throw new ChannelAlreadyExistsException();
        }
        BeanUtil.copyProperties(param,channel, CopyOptions.create().ignoreNullValue());
        return channelManager.updateById(channel).toDto();
    }

    /**
     * 获取单条
     */
    public ChannelDto findById(Long id){
        return channelManager.findById(id).map(Channel::toDto).orElse(null);
    }

    /**
     * 获取所有的渠道数据
     */
    public List<ChannelDto> findAll() {
        return channelManager.findAll().stream().map(Channel::toDto).collect(Collectors.toList());
    }

    /**
     * 获取指定类型的渠道数据
     */
    public List<ChannelDto> findByType(int type) {
        return channelManager.findAllByType(type).stream().map(Channel::toDto).collect(Collectors.toList());
    }

    /**
     * 根据渠道Key获取渠道对象
     */
    public List<ChannelDto> findByKey(String key) {
        if (StringUtil.isBlank(key)) {
            return Collections.emptyList();
        }
        return channelManager.findKey(key).stream().map(Channel::toDto).collect(Collectors.toList());
    }

    /**
     * 根据渠道name获取渠道对象
     */
    public ChannelDto findByName(String name) {
        if (StringUtil.isBlank(name)) {
            return null;
        }
        return channelManager.findByName(name).map(Channel::toDto).orElseThrow(() -> new BizException("未查到"));
    }

    /**
     * 根据ids查询
     */
    public List<ChannelDto> findByIds(Collection<Long> ids) {
        return channelManager.findAllByIds(ids).stream().map(Channel::toDto).collect(Collectors.toList());
    }

}
