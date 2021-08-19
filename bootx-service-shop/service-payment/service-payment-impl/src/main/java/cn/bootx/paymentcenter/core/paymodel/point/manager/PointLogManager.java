package cn.bootx.paymentcenter.core.paymodel.point.manager;

import cn.bootx.paymentcenter.core.paymodel.point.dao.PointLogRepository;
import cn.bootx.paymentcenter.core.paymodel.point.entity.PointLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
* 积分日志管理
* @author xxm
* @date 2020/12/11
*/
@Repository
@RequiredArgsConstructor
public class PointLogManager {
    private final PointLogRepository pointLogRepository;

    public PointLog addPointLog(Long userId, String description, String businessId, int points, String typeName) {
        PointLog pointLog = new PointLog();
        pointLog.setUserId(userId);
        pointLog.setPoints(points);
        pointLog.setDescription(description);
        pointLog.setBusinessId(businessId);
        pointLog.setTypeName(typeName);
        pointLogRepository.save(pointLog);
        return pointLog;
    }

}
