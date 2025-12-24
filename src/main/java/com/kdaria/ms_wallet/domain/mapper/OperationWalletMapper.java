package com.kdaria.ms_wallet.domain.mapper;

import com.kdaria.ms_wallet.domain.command.UpdateBalanceWalletCommand;
import com.kdaria.ms_wallet.domain.model.OperationWallet;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OperationWalletMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "sum", source = "amount")
  @Mapping(target = "type", source = "operationType")
  @Mapping(target = "createdDate", expression = "java(LocalDateTime.now())")
  OperationWallet map(UpdateBalanceWalletCommand command);
}
