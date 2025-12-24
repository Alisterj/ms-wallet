package com.kdaria.ms_wallet.presistence.repository;

import com.kdaria.ms_wallet.presistence.entity.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryTransactionRepository extends JpaRepository<HistoryTransactionEntity, Long> {}