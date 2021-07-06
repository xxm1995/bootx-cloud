package cn.bootx.bsp.core.dictionary.dao;

import cn.bootx.bsp.core.dictionary.entity.DictionaryItem;
import cn.bootx.bsp.core.dictionary.entity.QDictionaryItem;
import cn.bootx.common.web.rest.param.PageParam;
import cn.bootx.starter.headerholder.HeaderHolder;
import cn.bootx.starter.jpa.utils.JpaUtils;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 字典项
 * @author xxm
 * @date 2020/4/21 14:08
 */
@Repository
@AllArgsConstructor
public class DictionaryItemManager{

    private final DictionaryItemRepository dictionaryItemRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private final HeaderHolder headerHolder;

    public boolean existsByName(String name, Long dictionaryId){
        return dictionaryItemRepository.existsByNameAndDictionaryIdAndTid(name,dictionaryId,headerHolder.findTid());
    }

    public boolean existsByName(String name, Long dictionaryId, Long id){
        return dictionaryItemRepository.existsByNameAndDictionaryIdAndIdNotAndTid(name,dictionaryId,id,headerHolder.findTid());
    }

    public Optional<DictionaryItem> findById(Long id) {
        return dictionaryItemRepository.findByIdAndTid(id,headerHolder.findTid());
    }


    public List<DictionaryItem> listByIds(List<Long> dictionaryItemIds) {
        return dictionaryItemRepository.findAllByIdInAndTid(dictionaryItemIds,headerHolder.findTid());
    }

    /**
     * 查询指定目录下的所有内容
     */
    public List<DictionaryItem> findByDictionaryId(Long dictionaryId) {
        return dictionaryItemRepository.findByDictionaryIdAndTid(dictionaryId,headerHolder.findTid());
    }

    /**
     * 分页查询,根据字典Id
     */
    public Page<DictionaryItem> findAllByDictionaryId(Long dictionaryId, PageParam pageParam) {
        QDictionaryItem q = QDictionaryItem.dictionaryItem;
        JPAQuery<DictionaryItem> query = jpaQueryFactory.selectFrom(q)
                .where(
                        q.dictionaryId.eq(dictionaryId),
                        q.tid.eq(headerHolder.findTid())
                );
        return JpaUtils.queryPage(query,pageParam);
    }

    /**
     * 搜索 列表
     */
    public List<DictionaryItem> search(Long dictionaryId, String word) {
        QDictionaryItem q = QDictionaryItem.dictionaryItem;

        return jpaQueryFactory.selectFrom(q)
                .where(q.dictionaryId.eq(dictionaryId),
                        q.name.like("%"+word+"%"),
                        q.tid.eq(headerHolder.findTid()))
                .fetch();
    }

    /**
     * 搜索 分页
     */
    public Page<DictionaryItem> search(Long dictionaryId, String word, PageParam pageParam) {
        QDictionaryItem q = QDictionaryItem.dictionaryItem;
        JPAQuery<DictionaryItem> query = jpaQueryFactory.selectFrom(q)
                .where(q.dictionaryId.eq(dictionaryId),
                        q.name.like("%"+word+"%"),
                        q.tid.eq(headerHolder.findTid()));
        return JpaUtils.queryPage(query,pageParam);
    }
}
