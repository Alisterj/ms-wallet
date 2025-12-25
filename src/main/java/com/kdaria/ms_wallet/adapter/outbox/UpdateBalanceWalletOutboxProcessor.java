package com.kdaria.ms_wallet.adapter.outbox;

import com.kdaria.ms_wallet.domain.model.OperationWallet;
import com.kdaria.ms_wallet.domain.usecase.ProcessPendingOperationsUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Log4j2
@Component
@RequiredArgsConstructor
public class UpdateBalanceWalletOutboxProcessor {
  private final ProcessPendingOperationsUseCase processPendingOperationsUseCase;

  @Scheduled(initialDelay = 15, fixedDelay = 5, timeUnit = TimeUnit.SECONDS)
  public void processPendingEvent() {
    List<OperationWallet> operationWallets = processPendingOperationsUseCase.getOperationWallet();
    if (operationWallets.isEmpty()) {
      return;
    }

    log.info("Processing {} pending operations", operationWallets.size());

    for (OperationWallet operationWallet : operationWallets) {
      try {
        processPendingOperationsUseCase.updateBalance(operationWallet);
      } catch (Exception e) {
        log.error("Failed to process operation {}", operationWallet.getId(), e);
      }
    }
  }
}
