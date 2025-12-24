package com.kdaria.ms_wallet.presistence.service;

import com.kdaria.ms_wallet.domain.model.Wallet;
import com.kdaria.ms_wallet.domain.port.WalletProvider;
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
  public Optional<Wallet> findWalletById(@NotNull UUID uuid) {
    return walletRepository.findById(uuid).map(mapper::map);
  }

  @Override
  public void save(@NotNull Wallet wallet) {
    walletRepository.save(mapper.map(wallet));
  }

  @Override
  public void updateBalance(@NotNull UUID uuid, @NotNull BigDecimal amount) {
//    walletRepository.updateBalance(uuid, amount);
  }
}
