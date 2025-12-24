package com.kdaria.ms_wallet.domain.usecase;

import com.kdaria.ms_wallet.domain.mapper.OperationWalletMapper;
import com.kdaria.ms_wallet.domain.model.*;
import com.kdaria.ms_wallet.domain.port.*;
import com.kdaria.ms_wallet.en.OperationType;
import com.kdaria.ms_wallet.domain.command.UpdateBalanceWalletCommand;
import com.kdaria.ms_wallet.domain.exception.WalletNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateBalanceWalletUseCase {
  private final WalletProvider walletProvider;
  private final OperationWalletProvider operationWalletProvider;
  private final OperationWalletMapper mapper;

  @Transactional
  public void createUpdateRequest(@NotNull UpdateBalanceWalletCommand command) {
    walletProvider.findWalletById(command.walletId())
      .orElseThrow(() -> new WalletNotFoundException("the wallet was not found", command.walletId()));

    if (command.operationType() == OperationType.WITHDRAW) {
      walletProvider.checkWallet(command.walletId(), command.amount());
    }

    OperationWallet operationWallet = mapper.map(command);
    operationWalletProvider.save(operationWallet);
  }
}
