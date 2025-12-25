package com.kdaria.ms_wallet.persistence.repository;

import com.kdaria.ms_wallet.persistence.entity.OperationWalletEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface OperationWalletRepository extends JpaRepository<OperationWalletEntity, Long> {

  List<OperationWalletEntity> findAllBy(Pageable pageable);

}