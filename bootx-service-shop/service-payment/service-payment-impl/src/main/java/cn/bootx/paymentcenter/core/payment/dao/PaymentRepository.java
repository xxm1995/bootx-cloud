package cn.bootx.paymentcenter.core.payment.dao;

import cn.bootx.paymentcenter.core.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Modifying
    @Query(value = "UPDATE Payment SET payStatus = ?2 , errorCode = ?3 WHERE id = ?1 and tid =?4")
    int updatePayStatusAndTid(Long id, int status, String errorCode,Long tid);

    Optional<Payment> findByIdAndTid(Long id, Long tid);

    List<Payment> findByUserIdAndTid(Long userId, Long tid);
}
