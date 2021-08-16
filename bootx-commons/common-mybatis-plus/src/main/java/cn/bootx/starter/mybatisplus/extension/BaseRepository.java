package cn.bootx.starter.mybatisplus.extension;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 自定义的基础数据库Repository操作类
 * @author xxm
 * @date 2020/4/15 14:26
 */
public class BaseRepository<M extends BaseMapper<T>, T> extends ServiceImpl<M,T> {

    /**
     * idList 为空不报错
     * @param idList is集合
     * @return list
     */
    @Override
    public List<T> listByIds(Collection<? extends Serializable> idList) {
        if (CollUtil.isEmpty(idList)){
            return new ArrayList<T>(0);
        }
        return super.listByIds(idList);
    }

    /**
     * 根据指定字段查询是否存在数据
     * @param field 字段
     * @param fieldValue 字段数据
     * @return 是否存在
     */
    public boolean isExistedByField(SFunction<T, ?> field, Object fieldValue){
        return lambdaQuery().eq(field,fieldValue).count()>0;
    }

    /**
     * 根据指定字段查询存在的数据数量
     * @param field 字段
     * @param fieldValue 字段数据
     * @return 数量
     */
    public long countByField(SFunction<T, ?> field, Object fieldValue){
        return lambdaQuery().eq(field,fieldValue).count();
    }

    /**
     * 根据字段查询唯一值
     * @param field 字段
     * @param fieldValue 字段数据
     * @return 对象
     */
    public T getByField(SFunction<T, ?> field, Object fieldValue){
        return lambdaQuery().eq(field,fieldValue).one();
    }

    /**
     * 根据字段查询列表
     * @param field 字段
     * @param fieldValue 字段数据
     * @return 对象列表
     */
    public List<T> listByField(SFunction<T, ?> field, Object fieldValue){
        return lambdaQuery().eq(field,fieldValue).list();
    }

    /**
     * 根据指定字段值进行删除
     * @param field 字段
     * @param fieldValue 字段数据
     * @return boolean
     */
    public boolean removeByField(SFunction<T, ?> field, Object fieldValue){
        return lambdaUpdate().eq(field,fieldValue).remove();
    }
}
