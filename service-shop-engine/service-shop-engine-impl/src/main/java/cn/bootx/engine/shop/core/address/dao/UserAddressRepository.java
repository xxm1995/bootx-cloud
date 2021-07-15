package cn.bootx.engine.shop.core.address.dao;

import cn.bootx.engine.shop.core.address.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserAddressRepository extends JpaRepository<UserAddress,Long> {
    Optional<UserAddress> findByIdAndTid(Long id, Long tid);

    Optional<UserAddress> findByUserIdAndIsDefaultAndTid(Long userId,boolean isDefault, Long tid);

    @Modifying
    @Query("update UserAddress set isDefault = false where userId = :userId and isDefault = true and tid = :tid")
    void clearDefault(@Param("userId") Long userId,@Param("tid") Long tid);

    @Modifying
    @Query("update UserAddress set isDefault = true where id=:id and userId = :userId and tid = :tid")
    void setUpDefault(@Param("id") Long id,@Param("userId") Long userId,@Param("tid") Long tid);

    void deleteByIdAndUserIdAndTid(Long id,Long userId, Long tid);
}
