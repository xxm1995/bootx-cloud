package cn.bootx.bsp.client;

import cn.bootx.bsp.dto.channel.ChannelDto;
import cn.bootx.bsp.param.channel.ChannelParam;

import java.util.List;

/**   
* 渠道
* @author xxm  
* @date 2020/11/26 
*/
public interface ChannelClient {
    /**
     * 增加Channel
     */
    ChannelDto add(ChannelParam param);

    /**
     * 删除渠道
     */
    void delete(Long id);

    /**
     * 修改渠道数据
     */
    ChannelDto update(ChannelParam param);

    /**
     * 获取单条
     */
    ChannelDto findById(Long id);

    /**
     * 获取所有的渠道数据
     */
    List<ChannelDto> findAll();

    /**
     * 获取指定类型的渠道数据
     */
    List<ChannelDto> findByType(int type);

    /**
     * 根据渠道Key获取渠道对象
     */
    List<ChannelDto> findByKey(String key);

    /**
     * 根据渠道name获取渠道对象
     */
    ChannelDto findByName(String name);

    /**
     * 根据ids查询
     */
    List<ChannelDto> findByIds(List<Long> ids);
}
