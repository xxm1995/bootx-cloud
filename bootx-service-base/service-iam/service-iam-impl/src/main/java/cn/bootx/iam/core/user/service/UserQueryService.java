package cn.bootx.iam.core.user.service;

import cn.bootx.common.core.rest.PageResult;
import cn.bootx.common.core.rest.param.PageParam;
import cn.bootx.iam.core.user.dao.UserInfoManager;
import cn.bootx.iam.dto.user.UserInfoDto;
import cn.bootx.common.jpa.utils.JpaUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**   
* 用户查询
* @author xxm  
* @date 2021/7/19 
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class UserQueryService {
    private final UserInfoManager userInfoManager;

    /**
     * 分页查询
     */
    public PageResult<UserInfoDto> page(PageParam pageParam){
        return JpaUtils.convert2PageResult(userInfoManager.page(pageParam));
    }
}
