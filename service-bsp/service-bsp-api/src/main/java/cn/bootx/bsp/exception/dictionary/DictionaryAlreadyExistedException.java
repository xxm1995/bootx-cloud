package cn.bootx.bsp.exception.dictionary;


import cn.bootx.common.core.exception.BizException;

import java.io.Serializable;

import static cn.bootx.bsp.code.BspErrorCodes.DICTIONARY_ALREADY_EXISTED;


/**
* @author xxm
* @date 2020/4/10 15:14
*/
public class DictionaryAlreadyExistedException extends BizException implements Serializable {
    public DictionaryAlreadyExistedException() {
        super(DICTIONARY_ALREADY_EXISTED, "字典已经存在.");
    }
}
