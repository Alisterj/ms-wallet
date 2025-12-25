package com.kdaria.ms_wallet.persistence.mapper;

import com.kdaria.ms_wallet.domain.model.Wallet;
import com.kdaria.ms_wallet.persistence.entity.WalletEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface WalletEntityMapper {

  @Mapping(target = "operationType", ignore = true)
  @Mapping(target = "walletId", source = "id")
  @Mapping(target = "balance", source = "balance")
  Wallet map(WalletEntity e);

  @Mapping(target = "id", source = "walletId")
  @Mapping(target = "balance", source = "balance")
  WalletEntity map(Wallet wallet);
}
