package cn.bootx.starter.jpa.utils;

import cn.bootx.common.function.EntityBaseFunction;
import cn.bootx.common.web.rest.PageResult;
import cn.bootx.common.web.rest.param.PageParam;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
* Jpa工具类
* @author xxm  
* @date 2020/11/18 
*/
public class JpaUtils {

    /**
     * 获取分页对象 JPA
     */
    public static Pageable getPageable(PageParam page){
        return PageRequest.of(page.getCurrent()-1,page.getSize());
    }

    /**
     * QueryDsl 分页结果转换
     */
    public static <T> Page<T> queryPage(JPAQuery<T> jpaQuery, PageParam pageParam){
        QueryResults<T> queryResults = jpaQuery
                .offset(dslStart(pageParam))
                .limit(pageParam.getSize())
                .fetchResults();

        return new PageImpl<>(queryResults.getResults(),getPageable(pageParam),queryResults.getTotal());
    }

    /**
     * jpa page转换为 PageResult 同时进行dto转换
     */
    public static <T> PageResult<T> convert2PageResult(Page<? extends EntityBaseFunction<T>> page){
        if (Objects.isNull(page)){
            return new PageResult<>();
        }
        List<T> collect = page.getContent()
                .stream()
                .map(EntityBaseFunction::toDto)
                .collect(Collectors.toList());
        // 构造 Pageable 对象
        return new PageResult<T>()
                .setSize(page.getPageable().getPageSize())
                .setCurrent(page.getPageable().getPageNumber()+1)
                .setTotal(page.getTotalElements())
                .setRecords(collect);
    }

    /**
     * QueryDSL Start
     */
    public static long dslStart(PageParam page){
        return (page.getCurrent()-1)*page.getSize();
    }

    /**
     * QueryDSL End
     */
    public static long dslEnd(PageParam page){
        return page.getCurrent()*page.getSize();
    }
}
