package com.kdaria.ms_wallet.presistence.mapper;

import com.kdaria.ms_wallet.presistence.entity.OperationWalletEntity;
import com.kdaria.ms_wallet.domain.model.OperationWallet;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OperationWalletEntityMapper {

  OperationWalletEntity map(OperationWallet operationWallet);

  OperationWallet map(OperationWalletEntity operationWalletEntity);
}
