package com.kdaria.ms_wallet.persistence.mapper;

import com.kdaria.ms_wallet.persistence.entity.*;
import com.kdaria.ms_wallet.domain.model.OperationWallet;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OperationWalletEntityMapper {

  @Mapping(target = "id", source = "operationWallet.id")
  @Mapping(target = "wallet", source = "walletEntity")
  OperationWalletEntity map(OperationWallet operationWallet, WalletEntity walletEntity);

  @Mapping(target = "walletId", source = "operationWalletEntity.wallet.id")
  OperationWallet map(OperationWalletEntity operationWalletEntity);
}
