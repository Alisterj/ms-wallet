package com.kdaria.ms_wallet.persistence.repository;

import com.kdaria.ms_wallet.persistence.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryTransactionRepository extends JpaRepository<HistoryTransactionEntity, Long> {}