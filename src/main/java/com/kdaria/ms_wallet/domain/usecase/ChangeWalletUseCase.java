package com.kdaria.ms_wallet.domain.usecase;

import com.kdaria.ms_wallet.api.OperationType;
import com.kdaria.ms_wallet.domain.command.ChangeWalletCommand;
import com.kdaria.ms_wallet.domain.model.Wallet;
import com.kdaria.ms_wallet.domain.port.WalletProvider;
import com.kdaria.ms_wallet.domain.exception.WalletNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangeWalletUseCase {
  private final WalletProvider walletProvider;

  public void change(@NotNull ChangeWalletCommand command) {
    Wallet wallet = walletProvider.findWalletById(command.walletId())
      .orElseThrow(() -> new WalletNotFoundException("the wallet was not found", command.walletId()));

    if (command.operationType() == OperationType.DEPOSIT) {
      wallet.deposit(command.amount());
    } else {
      wallet.withdraw(command.amount());
    }

    walletProvider.save(wallet);
  }
}
