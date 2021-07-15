package cn.bootx.engine.shop.core.follow.dao;


import cn.bootx.engine.shop.core.follow.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

/**   
*
* @author xxm  
* @date 2021/2/18 
*/
public interface FollowRepository extends JpaRepository<Follow,Long> {
}
