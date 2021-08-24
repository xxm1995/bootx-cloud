package cn.bootx.bsp.exception.dict;

import cn.bootx.common.core.exception.BizException;

import java.io.Serializable;

import static cn.bootx.bsp.code.BspErrorCodes.DICTIONARY_ITEM_ALREADY_USED;


/**
* @author xxm
* @date 2020/4/21 11:54
*/
public class DictItemAlreadyUsedException extends BizException implements Serializable {
    public DictItemAlreadyUsedException() {
        super(DICTIONARY_ITEM_ALREADY_USED, "词典项目已被使用.");
    }
}
