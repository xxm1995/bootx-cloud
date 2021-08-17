package cn.bootx.bsp.exception.channel;

import cn.bootx.bsp.code.BspErrorCodes;
import cn.bootx.common.core.exception.BizException;


/**
* 渠道存在异常
* @author xxm
* @date 2020/10/16
*/
public class ChannelAlreadyExistsException extends BizException {

    public ChannelAlreadyExistsException() {
        super(BspErrorCodes.CHANNEL_ALREADY_EXISTS, "渠道已存在");
    }
}
