package com.kdaria.ms_wallet.persistence.service;

import com.kdaria.ms_wallet.domain.model.HistoryTransaction;
import com.kdaria.ms_wallet.domain.port.HistoryTransactionProvider;
import com.kdaria.ms_wallet.persistence.entity.*;
import com.kdaria.ms_wallet.persistence.mapper.HistoryTransactionEntityMapper;
import com.kdaria.ms_wallet.persistence.repository.*;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistoryTransactionService implements HistoryTransactionProvider {
  private final HistoryTransactionRepository historyTransactionRepository;
  private final WalletRepository walletRepository;
  private final HistoryTransactionEntityMapper mapper;

  public void save(@NotNull HistoryTransaction historyTransaction) {
    WalletEntity walletEntity = walletRepository.getReferenceById(historyTransaction.getWalletId());
    HistoryTransactionEntity entity = mapper.map(historyTransaction, walletEntity);
    historyTransactionRepository.save(entity);
  }
}
