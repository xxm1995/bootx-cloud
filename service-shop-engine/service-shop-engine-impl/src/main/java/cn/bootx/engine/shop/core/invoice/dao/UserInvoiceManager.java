package cn.bootx.engine.shop.core.invoice.dao;

import cn.bootx.engine.shop.core.invoice.entity.UserInvoice;
import cn.bootx.common.headerholder.HeaderHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
*
* @author xxm
* @date 2021/2/18
*/
@Repository
@RequiredArgsConstructor
public class UserInvoiceManager {
    private final UserInvoiceRepository userInvoiceRepository;
    private final HeaderHolder headerHolder;

    public void deleteById(Long id) {
        userInvoiceRepository.deleteByIdAndTid(id,headerHolder.findTid());
    }

    public Optional<UserInvoice> findById(Long id) {
        return userInvoiceRepository.findByIdAndTid(id,headerHolder.findTid());
    }
}
