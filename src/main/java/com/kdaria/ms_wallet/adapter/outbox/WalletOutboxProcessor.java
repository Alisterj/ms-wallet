package com.kdaria.ms_wallet.adapter.outbox;

import com.kdaria.ms_wallet.domain.exception.InsufficientFundsException;
import com.kdaria.ms_wallet.domain.usecase.*;
import com.kdaria.ms_wallet.presistence.entity.*;
import com.kdaria.ms_wallet.presistence.repository.OperationWalletRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class WalletOutboxProcessor {
  private final OperationWalletRepository operationWalletRepository;

  @Scheduled(fixedDelay = 1000)
  public void processPendingEvent() {
//    pagebale(100)
//    например 100 обрабатывет за раз
    List<OperationWalletEntity> entities = operationWalletRepository.findAllByCreatedDate();

    if (entities.isEmpty()) {
      return;
    }
    log.info("Processing {} outbox events...", entities.size());

    for (OperationWalletEntity event : entities) {
      painding(event);

      try {
      } catch (InsufficientFundsException e) {
        log.error("Insufficient funds in the account {}", event.getId(), e);
      } catch (Exception e) {
        log.error("Temporary error processing event {}", event.getId(), e);
      }
    }
  }

  @Transactional
  public void painding(@NotNull OperationWalletEntity operationWalletEntity) {
  }
}
