package com.kdaria.ms_wallet.domain.usecase;

import com.kdaria.ms_wallet.domain.exception.*;
import com.kdaria.ms_wallet.domain.model.*;
import com.kdaria.ms_wallet.domain.port.*;
import com.kdaria.ms_wallet.en.OperationType;
import com.kdaria.ms_wallet.presistence.entity.HistoryTransactionEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProcessPendingOperationsUseCase {
  private final WalletProvider walletProvider;
  private final OperationWalletProvider operationWalletProvider;
  private final HistoryTransactionProvider historyTransactionProvider;

  public List<OperationWallet> getOperationWallet() {
    PageRequest limit = PageRequest.of(0, 100, Sort.by("createdDate").ascending());
    return operationWalletProvider.findAllByCreatedDate(limit);
  }

  @Transactional
  public void updateBalance(OperationWallet operationWallet) {
    try {
      Wallet wallet = walletProvider.findWalletById(operationWallet.getWalletId())
        .orElseThrow(() -> new WalletNotFoundException("the wallet was not found", operationWallet.getWalletId()));

      if (operationWallet.getType() == OperationType.WITHDRAW &&
          wallet.getAmount().subtract(operationWallet.getSum()).compareTo(BigDecimal.ZERO) < 0) {
        operationWalletProvider.delete(Objects.requireNonNull(operationWallet.getId()));
        return;
      }

      walletProvider.updateBalance(
        wallet,
        operationWallet.getType(),
        operationWallet.getSum()
      );

      HistoryTransaction historyTransaction = new HistoryTransaction(
        null,
        operationWallet.getWalletId(),
        operationWallet.getSum(),
        operationWallet.getType(),
        operationWallet.getCreatedDate()
      );

      historyTransactionProvider.save(historyTransaction);
      operationWalletProvider.delete(Objects.requireNonNull(operationWallet.getId()));
    } catch (Exception e) {
      log.error("Failed to process operation {}: {}", operationWallet.getId(), e.getMessage());
      operationWalletProvider.delete(Objects.requireNonNull(operationWallet.getId()));
    }
  }
}
