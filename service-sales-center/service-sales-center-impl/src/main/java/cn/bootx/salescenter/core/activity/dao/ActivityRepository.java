package cn.bootx.salescenter.core.activity.dao;

import cn.bootx.salescenter.core.activity.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**   
*
* @author xxm  
* @date 2021/5/7 
*/
public interface ActivityRepository extends JpaRepository<Activity,Long> {
    List<Activity> findAllByIdInAndTid(List<Long> ids, Long tid);

    List<Activity> findAllByStrategyRegisterIdInAndTid(List<Long> ids, Long tid);
}
