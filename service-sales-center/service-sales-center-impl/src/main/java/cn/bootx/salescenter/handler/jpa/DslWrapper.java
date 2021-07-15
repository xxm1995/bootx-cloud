package cn.bootx.salescenter.handler.jpa;

import com.querydsl.core.types.Predicate;

import java.util.ArrayList;
import java.util.List;

/**   
* QueryDsl简单查询条件构造器
* @author xxm  
* @date 2020/11/1 
*/
public class DslWrapper {
    private final List<Predicate> list = new ArrayList<>();

    public DslWrapper of(boolean b, Predicate predicate){
        if (b){
            list.add(predicate);
        }
        return this;
    }

    public DslWrapper of(Predicate predicate){
        list.add(predicate);
        return this;
    }

    public Predicate[] to(){
        return list.toArray(new Predicate[0]);
    }
}
