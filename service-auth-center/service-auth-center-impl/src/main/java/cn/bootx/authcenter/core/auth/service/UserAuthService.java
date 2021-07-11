package cn.bootx.authcenter.core.auth.service;

import cn.bootx.authcenter.core.auth.dao.UserAuthManager;
import cn.bootx.authcenter.core.auth.dao.UserAuthRepository;
import cn.bootx.authcenter.core.auth.entity.UserAuth;
import cn.bootx.authcenter.core.session.UserAuthTokenService;
import cn.bootx.authcenter.dto.UserAuthDto;
import cn.bootx.authcenter.exception.*;
import cn.bootx.authcenter.param.UserAuthParam;
import cn.bootx.authcenter.utils.PasswordUtil;
import cn.bootx.usercenter.client.UserInfoClient;
import cn.bootx.usercenter.dto.user.UserInfoDto;
import cn.bootx.usercenter.exception.user.UserInfoNotExistsException;
import cn.bootx.usercenter.param.user.UserInfoParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;


/**
 * 用户认证
 * @author xxm
 * @date 2020/4/24 20:03
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserAuthService {
    private final UserInfoClient userInfoClient;

    private final UserAuthManager userAuthManager;
    private final UserAuthRepository userAuthRepository;
    private final AuthCodeService authCodeService;

    private final UserAuthTokenService userAuthTokenService;

    /**
     * 新增用户账号和用户信息
     */
    @Transactional(rollbackFor = Exception.class)
    public UserAuthDto saveUserAuthAndInfo(UserAuthParam authParam) {

        authParam.setId(null);

        // 保存用户信息
        UserInfoParam userInfoParam = Optional.ofNullable(authParam.getUserInfo())
                .orElseThrow(UserInfoNotExistsException::new);

        UserInfoDto userInfoDto = userInfoClient.addUserInfo(userInfoParam);

        authParam.setUid(userInfoDto.getId());

        //创建账号
        return this.addNew(authParam);
    }

    /**
     * 添加账户
     */
    @Transactional(rollbackFor = Exception.class)
    public UserAuthDto addNew(UserAuthParam authParam){
        if (userAuthManager.existsByUid(authParam.getUid())) {
            throw new UserAuthenticationAlreadyExistedException();
        }
        if (userAuthManager.existsByAccount(authParam.getAccount())) {
            throw new UserAccountAlreadyExistedException();
        }
        UserAuth userAuth = UserAuth.init(authParam);
        // 注册时间
        userAuth.setRegisterTime(LocalDateTime.now());
        //密码加密(注册时间做盐值)
        userAuth.setPassword(this.md5Password(authParam.getPassword(),userAuth.getPassword()));
        //默认激活
        userAuth.setActive(true);

        userAuth = userAuthRepository.save(userAuth);
        UserAuthDto dtoNew = userAuth.toDto();

        //获取用户信息并添加
        this.addUserInfo(dtoNew);
        return dtoNew;
    }

    /**
     * 获取用户信息并添加
     */
    private void addUserInfo(UserAuthDto userAuthDto) {
        if (Objects.nonNull(userAuthDto) && Objects.isNull(userAuthDto.getUserInfoDto())) {
            UserInfoDto userInfoDto = userInfoClient.getById(userAuthDto.getUid());
            userAuthDto.setUserInfoDto(userInfoDto);
        }
    }

    /**
     * 进行一次md5加密后, 再将注册时间作为盐值进行二次加密
     */
    private String md5Password(String password,String salt) {
        String md5Password = PasswordUtil.md5(password);
        return PasswordUtil.md5(md5Password,salt);
    }

    /**
     * 修改密码
     */
    @Transactional(rollbackFor = Exception.class)
    public void changePassword(Long userId, String passwordOld, String passwordNew) {
        //获取账户
        UserAuth authInfo = userAuthManager.findByUid(userId).orElseThrow(UserAuthenticationNotExistedException::new);

        String passwordOldMd5 = md5Password(passwordOld,authInfo.getAccount());
        if (!Objects.equals(passwordOldMd5,authInfo.getPassword())){
            throw new UserOldPasswordInvalidException();

        }
        // 新密码
        String passwordNewMd5 = md5Password(passwordNew,authInfo.getAccount());
        authInfo.setPassword(passwordNewMd5);
        userAuthRepository.save(authInfo);

        // 清除这个账户所有的authCode
        authCodeService.deleteByAuthId(authInfo.getId());
        // 剔除所有在线终端
        userAuthTokenService.kickedOutUser(authInfo.getTid(), authInfo.getUid());
    }

    /**
     * 激活账号
     */
    public UserAuthDto activeAccountById(Long id) {
        UserAuth authInfo = userAuthManager.findById(id)
                .orElseThrow(UserAccountNotExistedException::new);
        authInfo.setActive(true);
        // 返回账号信息
        UserAuthDto userAuthDto = userAuthRepository.save(authInfo).toDto();
        userAuthDto.setUserInfoDto(userInfoClient.getById(authInfo.getUid()));
        return userAuthDto;
    }
}
