package com.kdaria.ms_wallet.persistence.service;

import com.kdaria.ms_wallet.domain.model.Wallet;
import com.kdaria.ms_wallet.domain.port.WalletProvider;
import com.kdaria.ms_wallet.enums.OperationType;
import com.kdaria.ms_wallet.persistence.mapper.WalletEntityMapper;
import com.kdaria.ms_wallet.persistence.repository.WalletRepository;
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
  public void updateBalance(@NotNull Wallet wallet, @NotNull OperationType operationType, @NotNull BigDecimal amount) {
    if(operationType == OperationType.WITHDRAW) {
      amount = amount.negate();
    }
    walletRepository.updateBalance(wallet.getWalletId(), amount);
  }
}
