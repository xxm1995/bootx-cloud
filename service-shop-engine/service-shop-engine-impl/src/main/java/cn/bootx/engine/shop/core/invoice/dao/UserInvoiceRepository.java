package cn.bootx.engine.shop.core.invoice.dao;

import cn.bootx.engine.shop.core.invoice.entity.UserInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInvoiceRepository extends JpaRepository<UserInvoice, Long> {
    void deleteByIdAndTid(Long id, Long tid);

    Optional<UserInvoice> findByIdAndTid(Long id, Long tid);
}
