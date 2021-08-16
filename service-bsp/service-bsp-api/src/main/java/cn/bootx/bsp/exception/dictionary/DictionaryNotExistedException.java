package cn.bootx.bsp.exception.dictionary;


import cn.bootx.common.core.exception.BizException;

import java.io.Serializable;

import static cn.bootx.bsp.code.BspErrorCodes.DICTIONARY_NOT_EXISTED;


/**
* @author xxm
* @date 2020/4/21 11:53
*/
public class DictionaryNotExistedException extends BizException implements Serializable {
    public DictionaryNotExistedException() {
        super(DICTIONARY_NOT_EXISTED, "字典不存在.");
    }
}
