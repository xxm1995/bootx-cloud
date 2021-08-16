package cn.bootx.engine.shop.core.invoice.service;

import cn.bootx.common.core.exception.BizException;
import cn.bootx.engine.shop.core.invoice.dao.UserInvoiceManager;
import cn.bootx.engine.shop.core.invoice.dao.UserInvoiceRepository;
import cn.bootx.engine.shop.core.invoice.entity.UserInvoice;
import cn.bootx.engine.shop.dto.invoice.UserInvoiceDto;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**   
* 发票管理
* @author xxm  
* @date 2021/2/1 
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class UserInvoiceService {

    private final UserInvoiceRepository invoiceRepository;
    private final UserInvoiceManager invoiceManager;

    private final Long userId = 1L;
    /**
     * 添加发票信息
     */
    public UserInvoiceDto add(UserInvoiceDto param){
        UserInvoice userInvoice = UserInvoice.init(param);
        UserInvoice save = invoiceRepository.save(userInvoice);
        return save.toDto();
    }

    /**
     * 删除发票信息
     */
    public void delete(Long id){
        invoiceManager.deleteById(id);
    }

    /**
     * 更改发票信息
     */
    public void edit(UserInvoiceDto param){
        UserInvoice userInvoice = invoiceManager.findById(param.getId())
                .orElseThrow(() -> new BizException("发票信息不存在"));
        BeanUtil.copyProperties(param,userInvoice, CopyOptions.create().ignoreNullValue());
        invoiceRepository.save(userInvoice);
    }

    /**
     * 根据纳税人编号进行查询
     */
    public void getByContact(String contact){

    }

}
