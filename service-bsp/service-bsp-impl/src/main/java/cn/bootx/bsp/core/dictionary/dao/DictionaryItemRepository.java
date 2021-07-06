package cn.bootx.bsp.core.dictionary.dao;

import cn.bootx.bsp.core.dictionary.entity.DictionaryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
* 字典项
* @author xxm
* @date 2020/11/13
*/
public interface DictionaryItemRepository extends JpaRepository<DictionaryItem,Long> {

    /** 字典下是否已经存在指定名称字典项 */
    boolean existsByNameAndDictionaryIdAndTid(String name,Long dictionaryId,Long tid);

    /** 字典下是否已经存在指定名称字典项 */
    boolean existsByNameAndDictionaryIdAndIdNotAndTid(String name, Long dictionaryId, Long id, Long tid);

    Optional<DictionaryItem> findByIdAndTid(Long id, Long tid);

    /** 查询指定目录下的所有内容 */
    List<DictionaryItem> findByDictionaryIdAndTid(Long dictionaryId, Long tid);

    List<DictionaryItem> findAllByIdInAndTid(List<Long> dictionaryItemIds, Long tid);
}
