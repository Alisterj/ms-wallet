package com.kdaria.ms_wallet.presistence.service;

import com.kdaria.ms_wallet.domain.exception.*;
import com.kdaria.ms_wallet.domain.model.Wallet;
import com.kdaria.ms_wallet.domain.port.WalletProvider;
import com.kdaria.ms_wallet.en.OperationType;
import com.kdaria.ms_wallet.presistence.entity.WalletEntity;
import com.kdaria.ms_wallet.presistence.mapper.WalletEntityMapper;
import com.kdaria.ms_wallet.presistence.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class WalletService implements WalletProvider {
  private final WalletRepository walletRepository;
  private final WalletEntityMapper mapper;

  @Override
  public Optional<Wallet> findWalletById(@NotNull UUID walletId) {
    return walletRepository.findById(walletId).map(mapper::map);
  }

  @Override
  public void checkWallet(@NotNull UUID walletId, @NotNull BigDecimal amount) {
    WalletEntity entity = walletRepository.findById(walletId)
      .orElseThrow(() -> new WalletNotFoundException("the wallet was not found", walletId));

    if (entity.getBalance().compareTo(amount) < 0) {
      throw new InsufficientFundsException(
        String.format("Insufficient funds: balance is %s, attempted to withdraw %s",
          entity.getBalance(), amount)
      );
    }
  }

  @Override
  public void updateBalance(@NotNull UUID walletId, @NotNull OperationType operationType, @NotNull BigDecimal amount) {
    walletRepository.findById(walletId).orElseThrow(() -> new WalletNotFoundException("the wallet was not found", walletId));
    if(operationType == OperationType.WITHDRAW) {
      amount = amount.negate();
    }
    walletRepository.updateBalance(walletId, amount);
  }
}
