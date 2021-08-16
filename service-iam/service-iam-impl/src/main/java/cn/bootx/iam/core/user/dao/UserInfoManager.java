package cn.bootx.iam.core.user.dao;

import cn.bootx.common.core.rest.param.PageParam;
import cn.bootx.iam.core.user.entity.QUserInfo;
import cn.bootx.iam.core.user.entity.UserInfo;
import cn.bootx.starter.headerholder.HeaderHolder;
import cn.bootx.starter.jpa.utils.JpaUtils;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**   
* 用户信息
* @author xxm  
* @date 2020/4/24 15:32
*/
@Repository
@RequiredArgsConstructor
public class UserInfoManager {

    private final UserInfoRepository userInfoRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final HeaderHolder headerHolder;

    public Optional<UserInfo> findById(Long id) {
        return userInfoRepository.findByIdAndTid(id,headerHolder.findTid());
    }

    public boolean existsByAccount(String account) {
        return userInfoRepository.existsByAccountAndTid(account,headerHolder.findTid());
    }

    public boolean existsByEmail(String email) {
        return userInfoRepository.existsByEmailAndTid(email,headerHolder.findTid());
    }

    public boolean existsByPhone(String phone) {
        return userInfoRepository.existsByPhoneAndTid(phone,headerHolder.findTid());
    }

    public Optional<UserInfo> findByAccount(String account) {
        return userInfoRepository.findByAccountAndTid(account,headerHolder.findTid());
    }

    public Optional<UserInfo> findByEmail(String email) {
        return userInfoRepository.findByEmailAndTid(email,headerHolder.findTid());
    }

    public Optional<UserInfo> findByPhone(String phone) {
        return userInfoRepository.findByPhoneAndTid(phone,headerHolder.findTid());
    }

    public boolean existsById(Long id) {
        return userInfoRepository.existsByIdAndTid(id,headerHolder.findTid());
    }

    public List<UserInfo> findAllByIds(List<Long> ids) {
        return userInfoRepository.findByIdInAndTid(ids,headerHolder.findTid());
    }

    public Page<UserInfo> page(PageParam pageParam) {
        QUserInfo q = QUserInfo.userInfo;
        JPAQuery<UserInfo> userInfoJPAQuery = jpaQueryFactory.selectFrom(q);
        return JpaUtils.queryPage(userInfoJPAQuery,pageParam);
    }
}
