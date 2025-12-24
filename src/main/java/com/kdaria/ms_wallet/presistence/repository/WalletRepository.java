package com.kdaria.ms_wallet.presistence.repository;

import com.kdaria.ms_wallet.presistence.entity.WalletEntity;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface WalletRepository extends JpaRepository<WalletEntity, UUID> {
  
}
