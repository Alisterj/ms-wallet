package com.kdaria.ms_wallet.presistence.service;

import com.kdaria.ms_wallet.domain.model.OperationWallet;
import com.kdaria.ms_wallet.domain.port.OperationWalletProvider;
import com.kdaria.ms_wallet.presistence.mapper.OperationWalletEntityMapper;
import com.kdaria.ms_wallet.presistence.repository.OperationWalletRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OperationWalletService implements OperationWalletProvider {
  private final OperationWalletRepository operationWalletRepository;
  private final OperationWalletEntityMapper mapper;

  @Override
  public void save(@NotNull OperationWallet operationWallet) {
    operationWalletRepository.save(mapper.map(operationWallet));
  }
}
