package cn.bootx.bsp.client.feign;

import cn.bootx.bsp.client.DictionaryItemClient;
import cn.bootx.bsp.dto.dictionary.DictionaryItemDto;
import cn.bootx.common.core.rest.PageResult;
import cn.bootx.common.core.rest.param.PageParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
*
* @author xxm
* @date 2021/4/11
*/
@Component
@ConditionalOnMissingBean(DictionaryItemClient.class)
@RequiredArgsConstructor
public class DictionaryItemClientFeignImpl implements DictionaryItemClient {
    @Autowired(required = false)
    private DictionaryItemFeign dictionaryItemFeign;

    @Override
    public DictionaryItemDto add(DictionaryItemDto dictionaryItemDto) {
        return dictionaryItemFeign.add(dictionaryItemDto).getData();
    }

    @Override
    public void delete(Long id) {
        dictionaryItemFeign.delete(id);
    }

    @Override
    public DictionaryItemDto update(DictionaryItemDto dictionaryItemDto) {
        return dictionaryItemFeign.update(dictionaryItemDto).getData();
    }

    @Override
    public DictionaryItemDto findById(Long id) {
        return dictionaryItemFeign.findById(id).getData();
    }

    @Override
    public List<DictionaryItemDto> findByDictionaryId(Long dictionaryId) {
        return dictionaryItemFeign.findByDictionaryId(dictionaryId).getData();
    }

    @Override
    public PageResult<DictionaryItemDto> pageByDictionaryId(Long dictionaryId, PageParam pageParam) {
        return dictionaryItemFeign.pageByDictionaryId(dictionaryId,pageParam).getData();
    }

    @Override
    public PageResult<DictionaryItemDto> search(Long dictionaryId, String word, PageParam pageParam) {
        return dictionaryItemFeign.search(dictionaryId,word,pageParam).getData();
    }
}
