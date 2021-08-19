package cn.bootx.paymentcenter.core.paymodel.point.service;

import cn.bootx.common.core.exception.BizException;
import cn.bootx.paymentcenter.code.pay.PayStatusCode;
import cn.bootx.paymentcenter.code.paymodel.PointActionEnum;
import cn.bootx.paymentcenter.core.payment.entity.Payment;
import cn.bootx.paymentcenter.core.paymodel.point.dao.PointPaymentRepository;
import cn.bootx.paymentcenter.core.paymodel.point.dao.PointRecordRepository;
import cn.bootx.paymentcenter.core.paymodel.point.entity.PointPayment;
import cn.bootx.paymentcenter.core.paymodel.point.entity.PointRecord;
import cn.bootx.paymentcenter.core.paymodel.point.manager.PointLogManager;
import cn.bootx.paymentcenter.core.paymodel.point.manager.PointPaymentManager;
import cn.bootx.paymentcenter.core.paymodel.point.manager.PointRecordManager;
import cn.bootx.paymentcenter.dto.point.PointPaymentDto;
import cn.bootx.paymentcenter.exception.point.PointNotEnoughException;
import cn.bootx.paymentcenter.exception.point.PointNotFoundException;
import cn.bootx.paymentcenter.exception.point.PointPaymentNotFoundException;
import cn.bootx.paymentcenter.param.paymodel.point.PointParam;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 积分支付
 * @author xxm
 * @date 2021/2/28
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PointPayService {

    private final PointRecordRepository pointRecordRepository;
    private final PointRecordManager pointRecordManager;
    private final PointPaymentRepository pointPaymentRepository;
    private final PointPaymentManager pointPaymentManager;
    private final PointLogManager pointLogManager;

    private final PointService pointService;
    private final PointPaymentService pointPaymentService;

    /**
     * 取消支付
     */
    public void close(Payment payment){

        Optional.ofNullable(pointPaymentManager.findByPaymentId(payment.getId()))
                .ifPresent(pointPayments -> pointPayments.forEach(pointPayment -> {
                    if (pointPayment.getPayStatus() == PayStatusCode.TRADE_SUCCESS){
                        pointService.restorePointNotLog(pointPayment);
                    }
                    pointPayment.setPayStatus(PayStatusCode.TRADE_CANCEL);
                    pointPaymentRepository.save(pointPayment);
                }));

        PointPaymentDto paymentDto = pointPaymentService.getByPaymentId(payment.getId());
        //记录积分日志
        pointLogManager.addPointLog(payment.getUserId(), PointActionEnum.TYPE_REFUND_RESTORE.getName(),
                payment.getBusinessId(), paymentDto.getPoints(), PointActionEnum.TYPE_REFUND_RESTORE.getName());

    }

    /**
     * 消费积分
     */
    @Transactional(rollbackFor = Exception.class)
    public void consumePoint(int value, Payment payment, Long userId) {

        // FIXME: 多线程并发的情况下这段代码会有问题, 加入重入锁
        Integer point = pointRecordManager.findAvailablePoint(userId)
                .orElseThrow(() -> new BizException("积分未查到"));
        if ( point < value) {
            throw new PointNotEnoughException();
        }
        int points = value;
        List<PointRecord> needUpdatePointRecords = new ArrayList<>();
        List<PointPayment> pointConsumes = new ArrayList<>();

        //所有可用积分项
        List<PointRecord> pointRecordList = pointRecordManager.findAvailablePointGenerateRecords(userId);
        for (PointRecord pg : pointRecordList) {

            if (points == 0) {
                break;
            }
            int consumePoints;
            if (pg.getCurrentPoints() <= points) {
                points -= pg.getCurrentPoints();
                consumePoints = pg.getCurrentPoints();
                pg.setCurrentPoints(0);
            } else {
                pg.setCurrentPoints(pg.getCurrentPoints() - points);
                consumePoints = points;
                points = 0;
            }

            //生成消费记录
            PointPayment pointPayment = this.buildPointPayment(consumePoints, pg.getId(), payment, userId);
            pointConsumes.add(pointPayment);
            //记录要更新的积分生成记录
            needUpdatePointRecords.add(pg);
        }

        //持久化
        pointRecordRepository.saveAll(needUpdatePointRecords);
        pointPaymentRepository.saveAll(pointConsumes);
    }


    /**
     * 构建积分支付记录
     */
    private PointPayment buildPointPayment(int value, Long pgId, Payment payment, Long userId) {
        PointPayment pointPayment = new PointPayment()
                .setPointGenerateId(pgId)
                .setPoints(value);
        pointPayment.setPaymentId(payment.getId())
                .setUserId(userId)
                .setBusinessId(payment.getBusinessId())
                .setPayTime(LocalDateTime.now())
                .setPayStatus(PayStatusCode.TRADE_SUCCESS);
        return pointPayment;
    }

    /**
     * 返还积分(退款)
     */
    @Transactional(rollbackFor = Exception.class)
    public void restorePoint(PointParam pointParam) {

        //查询source下所有有效消费项  按积分生成id倒序
        List<PointPayment> pointPaymentList = pointPaymentManager.findByBusinessId(pointParam.getUserId(), pointParam.getBusinessId());
        //没有则抛异常
        if (CollectionUtil.isEmpty(pointPaymentList)) {
            throw new PointPaymentNotFoundException();
        }

        int points = pointParam.getValue();
        PointRecord pg;
        boolean isRestorePointEnd = false;
        List<PointRecord> pointRecordGenerates = new ArrayList<>();
        List<PointPayment> returnPointPaymentList = new ArrayList<>();

        //遍历返还积分
        for (PointPayment payment : pointPaymentList) {
            if (payment.getPoints() > points) {
                PointPayment paymentClone = payment.clone();
                paymentClone.setPoints(payment.getPoints() - points);
                returnPointPaymentList.add(paymentClone);

                payment.setPayStatus(PayStatusCode.TRADE_CANCEL);
                payment.setPoints(points);
                returnPointPaymentList.add(payment);

                isRestorePointEnd = true;
            } else if (payment.getPoints() == points) {
                payment.setPayStatus(PayStatusCode.TRADE_CANCEL);
                returnPointPaymentList.add(payment);
                isRestorePointEnd = true;
            } else {
                payment.setPayStatus(PayStatusCode.TRADE_CANCEL);
                points -= payment.getPoints();
                returnPointPaymentList.add(payment);
            }
            //创建积分生产记录
            pg = pointRecordManager.findById(payment.getPointGenerateId()).orElseThrow(PointNotFoundException::new);
            PointRecord pgClone = new PointRecord();
            BeanUtil.copyProperties(pg,pgClone);
            pgClone.setId(null);
            pgClone.setOriginPoints(payment.getPoints());
            pgClone.setCurrentPoints(payment.getPoints());
            pgClone.setType(PointActionEnum.TYPE_REFUND_RESTORE.getIndex());
            pointRecordGenerates.add(pgClone);

            if (isRestorePointEnd) {
                break;
            }
        }

        // 处理负积分
        pointService.redressNegativePoints(pointParam.getUserId(), pointRecordGenerates);

        //持久化
        pointPaymentRepository.saveAll(returnPointPaymentList);
        pointRecordRepository.saveAll(pointRecordGenerates);
    }
}
