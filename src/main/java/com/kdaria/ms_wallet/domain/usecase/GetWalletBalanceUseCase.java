package com.kdaria.ms_wallet.domain.usecase;

import com.kdaria.ms_wallet.domain.exception.WalletNotFoundException;
import com.kdaria.ms_wallet.domain.model.Wallet;
import com.kdaria.ms_wallet.domain.port.WalletProvider;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetWalletBalanceUseCase {
  private final WalletProvider walletProvider;

  @NotNull
  public BigDecimal getBalance(@NotNull UUID walletId) {
    return walletProvider.findWalletById(walletId)
      .map(Wallet::getAmount)
      .orElseThrow(() -> new WalletNotFoundException("the wallet was not found", walletId));
  }
}
