package com.kdaria.ms_wallet.domain.port;

import com.kdaria.ms_wallet.domain.model.HistoryTransaction;
import org.jetbrains.annotations.NotNull;

public interface HistoryTransactionProvider {

  void save(@NotNull HistoryTransaction historyTransaction);
}
