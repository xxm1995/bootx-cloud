package cn.bootx.bsp.core.app.service;

import cn.bootx.bsp.core.app.dao.AppVersionManager;
import cn.bootx.bsp.core.app.entity.AppVersion;
import cn.bootx.bsp.dto.app.AppVersionDto;
import cn.bootx.bsp.param.app.AppVersionParam;
import cn.bootx.common.core.rest.PageResult;
import cn.bootx.common.core.rest.param.PageParam;
import cn.bootx.common.mybatisplus.util.MpUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**   
* 版本管理
* @author xxm  
* @date 2021/8/9 
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class AppVersionService {
    private final AppVersionManager appVersionManager;

    /**
     * 添加
     */
    public AppVersionDto add(AppVersionParam param){
        AppVersion appVersion = AppVersion.init(param);
        return appVersionManager.save(appVersion).toDto();
    }

    /**
     * 删除
     */
    public void delete(Long id){
        appVersionManager.deleteById(id);
    }

    /**
     * 检查更新
     */
    public AppVersionDto check(){
        Optional<AppVersion> appVersion = appVersionManager.findLatest();
        return appVersion.map(AppVersion::toDto).orElse(null);
    }

    /**
     * 分页
     */
    public PageResult<AppVersionDto> page(PageParam pageParam){
        return MpUtils.convert2PageResult(appVersionManager.page(pageParam));
    }

    /**
     * 获取详情
     */
    public AppVersionDto findById(Long id){
        return appVersionManager.findById(id).map(AppVersion::toDto).orElse(null);
    }

}
