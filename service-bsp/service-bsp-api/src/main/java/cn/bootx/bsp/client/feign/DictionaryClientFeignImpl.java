package cn.bootx.bsp.client.feign;

import cn.bootx.bsp.client.DictionaryClient;
import cn.bootx.bsp.dto.dictionary.DictionaryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
*
* @author xxm
* @date 2021/4/11
*/
@Component
@ConditionalOnMissingBean(DictionaryClient.class)
@RequiredArgsConstructor
public class DictionaryClientFeignImpl implements DictionaryClient {
    private final DictionaryFeign dictionaryFeign;

    @Override
    public DictionaryDto add(DictionaryDto dictionaryDto) {
        return dictionaryFeign.add(dictionaryDto).getData();
    }

    @Override
    public void delete(Long id) {
        dictionaryFeign.delete(id);
    }

    @Override
    public DictionaryDto update(DictionaryDto dictionaryDto) {
        return dictionaryFeign.update(dictionaryDto).getData();
    }

    @Override
    public DictionaryDto findById(Long id) {
        return dictionaryFeign.findById(id).getData();
    }

    @Override
    public List<DictionaryDto> findAll() {
        return dictionaryFeign.findAll().getData();
    }
}
