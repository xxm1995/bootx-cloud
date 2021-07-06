package cn.bootx.bsp.exception.dictionary;


import cn.bootx.common.web.exception.BizException;

import java.io.Serializable;

import static cn.bootx.bsp.code.BspErrorCodes.DICTIONARY_ITEM_ALREADY_EXISTED;


/**
* @author xxm
* @date 2020/4/21 11:53
*/
public class DictionaryItemAlreadyExistedException extends BizException implements Serializable {
    public DictionaryItemAlreadyExistedException() {
        super(DICTIONARY_ITEM_ALREADY_EXISTED, "字典项目已存在.");
    }
}
