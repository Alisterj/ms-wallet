package com.kdaria.ms_wallet.presistence.mapper;

import com.kdaria.ms_wallet.domain.model.Wallet;
import com.kdaria.ms_wallet.presistence.entity.WalletEntity;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface WalletEntityMapper {

  @Mapping(target = "operationType", ignore = true)
  @Mapping(target = "walletId", source = "id")
  @Mapping(target = "amount", source = "balance")
  Wallet map(WalletEntity e);

  @Mapping(target = "id", source = "walletId")
  @Mapping(target = "balance", source = "amount")
  WalletEntity map(Wallet wallet);
}
