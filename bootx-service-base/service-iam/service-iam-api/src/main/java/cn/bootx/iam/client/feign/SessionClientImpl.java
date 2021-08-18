package cn.bootx.iam.client.feign;

import cn.bootx.common.core.entity.UserDetail;
import cn.bootx.iam.client.SessionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**   
* 会话
* @author xxm  
* @date 2021/8/18 
*/
@Component
public class SessionClientImpl implements SessionClient {
    @Autowired(required = false)
    private SessionFeign sessionFeign;
    @Override
    public UserDetail getUserDetail() {
        return sessionFeign.getUserDetail().getData();
    }
}
