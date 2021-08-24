package cn.bootx.bsp.exception.dict;


import cn.bootx.common.core.exception.BizException;

import java.io.Serializable;

import static cn.bootx.bsp.code.BspErrorCodes.CHILD_ITEM_EXISTED;


/**
* @author xxm
* @date 2020/4/16 22:08
*/
public class DictChildItemExistedException extends BizException implements Serializable {
    private static final long serialVersionUID = -3964173905076738575L;

    public DictChildItemExistedException() {
        super(CHILD_ITEM_EXISTED, "存在字典子项，您无法将其删除。");
    }
}
