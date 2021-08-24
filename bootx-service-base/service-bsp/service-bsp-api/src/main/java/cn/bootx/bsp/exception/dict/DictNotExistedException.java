package cn.bootx.bsp.exception.dict;


import cn.bootx.common.core.exception.BizException;

import java.io.Serializable;

import static cn.bootx.bsp.code.BspErrorCodes.DICTIONARY_NOT_EXISTED;


/**
* @author xxm
* @date 2020/4/21 11:53
*/
public class DictNotExistedException extends BizException implements Serializable {
    public DictNotExistedException() {
        super(DICTIONARY_NOT_EXISTED, "字典不存在.");
    }
}
