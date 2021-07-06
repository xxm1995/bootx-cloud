package cn.bootx.noticecenter.core.sms.dao;

import cn.bootx.noticecenter.core.sms.entity.SmsConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**   
* @author xxm
* @date 2020/4/8 13:23 
*/
public interface SmsConfigRepository extends JpaRepository<SmsConfig,Long> {
}
