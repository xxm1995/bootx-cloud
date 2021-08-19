package cn.bootx.paymentcenter.core.paymodel.point.service;

import cn.bootx.paymentcenter.core.paymodel.point.dao.PointRecordRepository;
import cn.bootx.paymentcenter.core.paymodel.point.entity.PointPayment;
import cn.bootx.paymentcenter.core.paymodel.point.manager.PointPaymentManager;
import cn.bootx.paymentcenter.dto.point.PointPaymentDto;
import cn.hutool.core.collection.CollectionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 积分支付记录
* @author xxm
* @date 2021/3/2
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class PointPaymentService {
    private final PointRecordRepository pointRecordRepository;
    private final PointPaymentManager pointPaymentManager;

    /**
     * 根据PaymentId获取支付信息
     */
    public PointPaymentDto getByPaymentId(Long paymentId) {
        List<PointPayment> pointPayments = pointPaymentManager.findByPaymentId(paymentId);

        PointPaymentDto pointPaymentDto = null;
        if (!CollectionUtil.isEmpty(pointPayments)) {
            pointPaymentDto = new PointPaymentDto();
            BeanUtils.copyProperties(pointPayments.get(0), pointPaymentDto);

            int points = pointPayments.stream().mapToInt(PointPayment::getPoints).sum();
            pointPaymentDto.setPoints(points);
        }

        return pointPaymentDto;
    }

    /**
     * 根据paymentId获取PointPayment
     */
    public List<PointPayment> findByPaymentId(Long paymentId) {
        return pointPaymentManager.findByPaymentId(paymentId);
    }
}
