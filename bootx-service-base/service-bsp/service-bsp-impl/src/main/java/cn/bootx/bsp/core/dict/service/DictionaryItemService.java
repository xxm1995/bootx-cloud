package cn.bootx.bsp.core.dict.service;

import cn.bootx.bsp.core.dict.dao.DictionaryItemManager;
import cn.bootx.bsp.core.dict.dao.DictionaryManager;
import cn.bootx.bsp.core.dict.entity.Dictionary;
import cn.bootx.bsp.core.dict.entity.DictionaryItem;
import cn.bootx.bsp.dto.dict.DictionaryItemDto;
import cn.bootx.bsp.exception.dict.DictItemAlreadyExistedException;
import cn.bootx.bsp.exception.dict.DictItemNotExistedException;
import cn.bootx.bsp.param.dict.DictionaryItemParam;
import cn.bootx.common.core.exception.BizException;
import cn.bootx.common.core.rest.PageResult;
import cn.bootx.common.core.rest.param.PageParam;
import cn.bootx.common.core.util.ResultConvertUtils;
import cn.bootx.common.mybatisplus.util.MpUtils;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xxm
 * @date 2020/4/16 21:16
 */
@Service
@AllArgsConstructor
public class DictionaryItemService {
    private final DictionaryItemManager dictionaryItemManager;
    private final DictionaryManager dictionaryManager;

    /**
     * 添加内容
     */
    @Transactional(rollbackFor = Exception.class)
    public DictionaryItemDto add(DictionaryItemParam param){

        // 在同一个Dictionary不允许存在相同名字的DictionaryItem
        if (dictionaryItemManager.existsByName(param.getName(), param.getDictId())) {
            throw new DictItemAlreadyExistedException();
        }

        Dictionary dictionary = dictionaryManager.findById(param.getDictId()).orElseThrow(() -> new BizException("字典不存在"));
        param.setDictCode(dictionary.getCode());
        DictionaryItem dictionaryItem = DictionaryItem.init(param);
        dictionaryItem =dictionaryItemManager.save(dictionaryItem);
        return dictionaryItem.toDto();
    }

    /**
     * 修改内容
     */
    @Transactional(rollbackFor = Exception.class)
    public DictionaryItemDto update(DictionaryItemParam param) {

        // 判断字典item是否存在
        DictionaryItem dictionaryItem = dictionaryItemManager.findById(param.getId())
                .orElseThrow(DictItemNotExistedException::new);

        // 判断是否有重名的Item
        if (dictionaryItemManager.existsByName(param.getName(),param.getDictId(),param.getId())) {
            throw new DictItemAlreadyExistedException();
        }
        BeanUtil.copyProperties(param,dictionaryItem, CopyOptions.create().ignoreNullValue());
        return dictionaryItemManager.updateById(dictionaryItem).toDto();
    }

    /**
     * 删除内容
     */
    public void delete(Long id) {
        dictionaryItemManager.deleteById(id);
    }

    /**
     * 根据ID查询指定内容
     */
    public DictionaryItemDto findById(Long id) {
        return dictionaryItemManager.findById(id).map(DictionaryItem::toDto).orElse(null);
    }

    /**
     * 查询指定目录下的所有内容
     */
    public List<DictionaryItemDto> findByDictionaryId(Long dictionaryId) {
        return dictionaryItemManager.findByDictId(dictionaryId)
                .stream()
                .map(DictionaryItem::toDto)
                .collect(Collectors.toList());
    }

    /**
     * 分页查询指定目录下的内容
     */
    public PageResult<DictionaryItemDto> pageByDictionaryId(Long dictionaryId, PageParam pageParam) {
        Page<DictionaryItem> dictionaryItems = dictionaryItemManager.findAllByDictionaryId(dictionaryId, pageParam);
        return MpUtils.convert2PageResult(dictionaryItems);
    }

    /**
     * 根据type 查询字典
     */
    public List<DictionaryItemDto> findByDictCode(String dictCode){
        return ResultConvertUtils.dtoListConvert(dictionaryItemManager.findByDictCode(dictCode));
    }
}
