package com.kdaria.ms_wallet.presistence.repository;

import com.kdaria.ms_wallet.presistence.entity.OperationWalletEntity;
import org.springframework.data.jpa.repository.*;

import java.util.List;

public interface OperationWalletRepository extends JpaRepository<OperationWalletEntity, Long> {

  List<OperationWalletEntity> findAllByCreatedDate();
}
