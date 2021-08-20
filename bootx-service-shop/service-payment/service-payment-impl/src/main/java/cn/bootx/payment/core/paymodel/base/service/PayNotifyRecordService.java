package cn.bootx.payment.core.paymodel.base.service;

import cn.bootx.common.core.rest.PageResult;
import cn.bootx.common.core.rest.param.PageParam;
import cn.bootx.common.mybatisplus.util.MpUtils;
import cn.bootx.payment.core.paymodel.base.dao.PayNotifyRecordManager;
import cn.bootx.payment.core.paymodel.base.entity.PayNotifyRecord;
import cn.bootx.payment.dto.pay.PayNotifyRecordDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    /**
     * 保存回调记录
     */
    public void saveNotifyRecord(PayNotifyRecord payNotifyRecord){
        payNotifyRecordManager.save(payNotifyRecord);
    }

    /**
     * 分页查询
     */
    public PageResult<PayNotifyRecordDto> page(PageParam pageParam){
        Page<PayNotifyRecord> page = payNotifyRecordManager.page(pageParam);
        return MpUtils.convert2PageResult(page);
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
