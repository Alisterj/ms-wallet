package com.kdaria.ms_wallet.domain.usecase;

import com.kdaria.ms_wallet.domain.exception.*;
import com.kdaria.ms_wallet.domain.model.*;
import com.kdaria.ms_wallet.domain.port.*;
import com.kdaria.ms_wallet.enums.OperationType;
import com.kdaria.ms_wallet.utils.Utils;
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
    PageRequest limit = PageRequest.of(0, 2000, Sort.by("createdDate").ascending());
    return operationWalletProvider.findAllByCreatedDate(limit);
  }

  @Transactional
  public void updateBalance(OperationWallet operationWallet) {
    Wallet wallet = walletProvider.findWalletById(operationWallet.getWalletId())
      .orElseThrow(() -> new WalletNotFoundException("Wallet was not found. WalletId:", operationWallet.getWalletId()));

    if (Utils.invalidWithdrawal(operationWallet.getType(), wallet.getBalance(), operationWallet.getSum())) {
      operationWalletProvider.delete(Objects.requireNonNull(operationWallet.getId()));
      return;
    }

    walletProvider.updateBalance(wallet, operationWallet.getType(), operationWallet.getSum());

    historyTransactionProvider.save(new HistoryTransaction(
      null,
      operationWallet.getWalletId(),
      operationWallet.getSum(),
      operationWallet.getType(),
      operationWallet.getCreatedDate()
    ));

    operationWalletProvider.delete(Objects.requireNonNull(operationWallet.getId()));
  }
}
