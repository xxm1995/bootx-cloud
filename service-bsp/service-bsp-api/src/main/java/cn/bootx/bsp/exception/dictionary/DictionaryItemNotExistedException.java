package cn.bootx.bsp.exception.dictionary;


import cn.bootx.common.web.exception.BizException;

import java.io.Serializable;

import static cn.bootx.bsp.code.BspErrorCodes.DICTIONARY_ITEM_NOT_EXISTED;


/**
* @author xxm
* @date 2020/4/21 11:54
*/
public class DictionaryItemNotExistedException extends BizException implements Serializable {
    public DictionaryItemNotExistedException() {
        super(DICTIONARY_ITEM_NOT_EXISTED, "字典项目不存在.");
    }
}
