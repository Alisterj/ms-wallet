package com.kdaria.ms_wallet.presistence.service;

import com.kdaria.ms_wallet.domain.model.OperationWallet;
import com.kdaria.ms_wallet.domain.port.OperationWalletProvider;
import com.kdaria.ms_wallet.presistence.entity.*;
import com.kdaria.ms_wallet.presistence.mapper.OperationWalletEntityMapper;
import com.kdaria.ms_wallet.presistence.repository.*;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationWalletService implements OperationWalletProvider {
  private final OperationWalletRepository operationWalletRepository;
  private final WalletRepository walletRepository;
  private final OperationWalletEntityMapper mapper;

  @Override
  public void save(@NotNull OperationWallet operationWallet) {
    WalletEntity walletEntity = walletRepository.getReferenceById(operationWallet.getWalletId());
    operationWalletRepository.save(mapper.map(operationWallet, walletEntity));
  }

  @Override
  public List<OperationWallet> findAllByCreatedDate(@NotNull Pageable pageable) {
    List<OperationWalletEntity> entities = operationWalletRepository.findAllBy(pageable);
    return entities.stream().map(mapper::map).toList();
  }

  @Override
  public void delete(@NotNull Long id) {
    operationWalletRepository.deleteById(id);
  }
}
