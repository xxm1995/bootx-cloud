package cn.bootx.paymentcenter.core.paymodel.point.service;

import cn.bootx.common.core.exception.BizException;
import cn.bootx.paymentcenter.code.paymodel.PointActionEnum;
import cn.bootx.paymentcenter.core.payment.entity.Payment;
import cn.bootx.paymentcenter.core.paymodel.point.dao.PointRecordRepository;
import cn.bootx.paymentcenter.core.paymodel.point.entity.PointPayment;
import cn.bootx.paymentcenter.core.paymodel.point.entity.PointRecord;
import cn.bootx.paymentcenter.core.paymodel.point.manager.PointLogManager;
import cn.bootx.paymentcenter.core.paymodel.point.manager.PointRecordManager;
import cn.bootx.paymentcenter.exception.point.PointNotFoundException;
import cn.bootx.paymentcenter.param.paymodel.point.PointParam;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.csp.sentinel.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 积分
 * @author xxm
 * @date 2020/12/11
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PointService {
    private final PointRecordManager pointRecordManager;
    private final PointRecordRepository pointRecordRepository;
    private final PointLogManager pointLogManager;

    /**
     * 生产积分
     */
    @Transactional(rollbackFor = Exception.class)
    public void addPoint(PointParam param) {

        // 获取积分类型
        String typeName = PointActionEnum.getByIndex(param.getType()).getName();

        // 处理负积分
        int leftPointsCount = this.redressNegativePoints(param.getUserId(), param.getValue());

        // 保存积分
        PointRecord pointRecord = new PointRecord();
        BeanUtils.copyProperties(param, pointRecord);
        pointRecord.setOriginPoints(param.getValue())
                .setCurrentPoints(leftPointsCount)
                .setExpireDate(LocalDateTime.now().plusYears(1));
        pointRecordRepository.save(pointRecord);

        String desc = param.getDescription();
        if (StringUtil.isEmpty(desc)) {
            desc = typeName;
        }
        pointLogManager.addPointLog(param.getUserId(), desc, param.getBusinessId(), param.getValue(), typeName);
    }

    /**
     * 获取可用积分
     */
    public Integer getAvailablePoint(Long userId) {
        return pointRecordManager.findAvailablePoint(userId)
                .orElseThrow(() -> new BizException("积分不存在"));
    }

    /**
     * 处理负积分
     * 处理完负积分后剩余积分
     */
    public int redressNegativePoints(Long userId, int pointCount) {
        List<PointRecord> negativePointRecords = pointRecordManager.findNegativePoints(userId);
        if (CollectionUtil.isEmpty(negativePointRecords)) {
            return pointCount;
        }
        for (PointRecord pointRecord : negativePointRecords) {
            pointCount = pointCount + pointRecord.getCurrentPoints();
            if (pointCount > 0) {
                pointRecord.setCurrentPoints(0);
                pointRecordRepository.save(pointRecord);
            } else {
                pointRecord.setCurrentPoints(pointCount);
                pointRecordRepository.save(pointRecord);
                break;
            }
        }
        return Math.max(pointCount, 0);
    }

    /**
     * 处理负积分
     */
    public List<PointRecord> redressNegativePoints(Long userId, List<PointRecord> pointRecords) {
        List<PointRecord> negativePointRecords = pointRecordManager.findNegativePoints(userId);
        if (CollectionUtil.isEmpty(negativePointRecords)) {
            return pointRecords;
        }
        int negativeSum = negativePointRecords.stream().mapToInt(PointRecord::getCurrentPoints).sum();
        int restoreSum = pointRecords.stream().mapToInt(PointRecord::getCurrentPoints).sum();
        // 返还的积分多
        if (negativeSum + restoreSum > 0) {
            negativePointRecords.forEach(pointGenerate -> pointGenerate.setCurrentPoints(0));
            int leftPointCount = -negativeSum;
            for (PointRecord pointRecord : pointRecords) {
                leftPointCount -= pointRecord.getCurrentPoints();
                // 还没扣完
                if (leftPointCount > 0) {
                    pointRecord.setCurrentPoints(0);
                } else { // 扣完了
                    pointRecord.setCurrentPoints(-leftPointCount);
                    break;
                }
            }
        } else {// 返还的积分少
            pointRecords.forEach(pointGenerate -> pointGenerate.setCurrentPoints(0));
            int leftPointCount = restoreSum;
            for (PointRecord pointRecord : negativePointRecords) {
                leftPointCount += pointRecord.getCurrentPoints();
                // 还没扣完
                if (leftPointCount > 0) {
                    pointRecord.setCurrentPoints(0);
                } else { // 扣完了
                    pointRecord.setCurrentPoints(leftPointCount);
                    break;
                }
            }
        }
        pointRecordRepository.saveAll(negativePointRecords);
        return pointRecords;
    }


    /**
     * 恢复积分-不记录日志等
     */
    public void restorePointNotLog(PointPayment pointPayment) {
        PointRecord pointRecord = pointRecordManager.findById(pointPayment.getPointGenerateId()).orElseThrow(PointNotFoundException::new);
        pointRecord.setCurrentPoints(pointRecord.getCurrentPoints() + pointPayment.getPoints());
        pointRecordRepository.save(pointRecord);
    }


    /**
     * 记录积分日志方法
     */
    public void addPointLog(int value, Payment payment, Long userId) {
        pointLogManager.addPointLog(userId, PointActionEnum.TYPE_PURCHASE_CONSUME.getName(), payment.getBusinessId(), -value, PointActionEnum.TYPE_PURCHASE_CONSUME.getName());
    }

    /**
     * 摧毁积分
     */
    @Transactional(rollbackFor = Exception.class)
    public void destroyPoint(PointParam pointParam) {
        int points = pointParam.getValue();
        //由source产生的积分是否足够  本源积分指生成的最原始积分 返还的不算
        PointRecord sourcePoint = pointRecordManager.findPointByBusinessId(pointParam.getUserId(), pointParam.getBusinessId()).orElseThrow(PointNotFoundException::new);
        int sourcePoints = sourcePoint != null ? sourcePoint.getCurrentPoints() : 0;

        if (sourcePoints < points) {
            if (sourcePoints > 0) {
                //先摧毁本源积分
                sourcePoint.setCurrentPoints(0);
                pointRecordRepository.save(sourcePoint);
                points -= sourcePoints;
            }
            //摧毁其他源积分 优先摧毁有效期较早的
            List<PointRecord> pointRecordGenerateList = pointRecordManager.findAvailablePointGenerateRecords(pointParam.getUserId());
            List<PointRecord> needUpdatePointRecordGenerates = new ArrayList<>();
            for (PointRecord pg : pointRecordGenerateList) {
                if (Objects.nonNull(sourcePoint) && pg.getId().equals(sourcePoint.getId())) {
                    continue;
                }

                if (points == 0) {
                    break;
                }

                if (pg.getCurrentPoints() <= points) {
                    points -= pg.getCurrentPoints();
                    pg.setCurrentPoints(0);
                } else {
                    pg.setCurrentPoints(pg.getCurrentPoints() - points);
                    points = 0;
                }
                //记录要更新的积分生成记录
                needUpdatePointRecordGenerates.add(pg);
            }
            pointRecordRepository.saveAll(needUpdatePointRecordGenerates);
        } else {
            //只摧毁本源积分
            if (sourcePoint != null) {
                sourcePoint.setCurrentPoints(sourcePoints - points);
                pointRecordRepository.save(sourcePoint);
                points = 0;
            }
        }
        // 用户积分不够扣 将本源积分扣成负值
        if (points > 0) {
            // 查询原始订单的积分记录
            PointRecord sourcePointRecordGenerate = pointRecordManager.findByBusinessId(pointParam.getBusinessId()).orElseThrow(PointNotFoundException::new);
            if (Objects.nonNull(sourcePointRecordGenerate)) {
                sourcePointRecordGenerate.setCurrentPoints(sourcePointRecordGenerate.getCurrentPoints() - points);
                pointRecordRepository.save(sourcePointRecordGenerate);
            }
            points = 0;
        }

        //记录积分日志
        PointActionEnum pointAction = PointActionEnum.TYPE_REVOKE;
        if (Objects.nonNull(pointParam.getType())) {
            pointAction = PointActionEnum.getByIndex(pointParam.getType());
        }
        int pointNum = -pointParam.getValue() + points;
        pointLogManager.addPointLog(pointParam.getUserId(), pointAction.getDescription(), pointParam.getBusinessId(), pointNum, pointAction.getName());
    }
}
