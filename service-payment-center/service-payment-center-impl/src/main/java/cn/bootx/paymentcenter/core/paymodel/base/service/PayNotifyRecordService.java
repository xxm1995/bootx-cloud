package cn.bootx.paymentcenter.core.paymodel.base.service;

import cn.bootx.common.web.rest.PageResult;
import cn.bootx.common.web.rest.param.PageParam;
import cn.bootx.paymentcenter.core.paymodel.base.dao.PayNotifyRecordManager;
import cn.bootx.paymentcenter.core.paymodel.base.dao.PayNotifyRecordRepository;
import cn.bootx.paymentcenter.core.paymodel.base.entity.PayNotifyRecord;
import cn.bootx.paymentcenter.dto.pay.PayNotifyRecordDto;
import cn.bootx.starter.jpa.utils.JpaUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**   
* 回调记录
* @author xxm  
* @date 2021/7/5 
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class PayNotifyRecordService {
    private final PayNotifyRecordManager payNotifyRecordManager;
    private final PayNotifyRecordRepository payNotifyRecordRepository;

    /**
     * 保存回调记录
     */
    public void saveNotifyRecord(PayNotifyRecord payNotifyRecord){
        payNotifyRecordRepository.save(payNotifyRecord);
    }

    /**
     * 分页查询
     */
    public PageResult<PayNotifyRecordDto> page(PageParam pageParam){
        Page<PayNotifyRecord> page = payNotifyRecordManager.page(pageParam);
        return JpaUtils.convert2PageResult(page);
    }

    /**
     * 根据id查询
     */
    public PayNotifyRecordDto findById(Long id){
        return payNotifyRecordManager.findById(id)
                .map(PayNotifyRecord::toDto)
                .orElse(null);
    }
}
