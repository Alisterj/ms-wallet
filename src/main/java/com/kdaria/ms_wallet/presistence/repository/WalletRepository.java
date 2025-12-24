package com.kdaria.ms_wallet.presistence.repository;

import com.kdaria.ms_wallet.presistence.entity.WalletEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.*;

public interface WalletRepository extends JpaRepository<WalletEntity, UUID> {

  @Modifying
  @Query("UPDATE WalletEntity SET balance = balance + :amount WHERE id = :uuid")
  void updateBalance(@Param("uuid") UUID uuid,
                     @Param("amount") BigDecimal amount);
}
