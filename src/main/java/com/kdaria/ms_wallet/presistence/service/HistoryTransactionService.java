package com.kdaria.ms_wallet.presistence.service;

import com.kdaria.ms_wallet.domain.model.HistoryTransaction;
import com.kdaria.ms_wallet.domain.port.HistoryTransactionProvider;
import com.kdaria.ms_wallet.presistence.entity.HistoryTransactionEntity;
import com.kdaria.ms_wallet.presistence.mapper.HistoryTransactionEntityMapper;
import com.kdaria.ms_wallet.presistence.repository.HistoryTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistoryTransactionService implements HistoryTransactionProvider {
  private final HistoryTransactionRepository historyTransactionRepository;
  private final HistoryTransactionEntityMapper mapper;

  public void save(@NotNull HistoryTransaction historyTransaction) {
    HistoryTransactionEntity entity = mapper.map(historyTransaction);
    historyTransactionRepository.save(entity);
  }
}
