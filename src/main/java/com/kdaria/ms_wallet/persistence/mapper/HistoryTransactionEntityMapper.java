package com.kdaria.ms_wallet.persistence.mapper;

import com.kdaria.ms_wallet.domain.model.HistoryTransaction;
import com.kdaria.ms_wallet.persistence.entity.*;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface HistoryTransactionEntityMapper {

  @Mapping(target = "id", source = "historyTransaction.id")
  @Mapping(target = "wallet", source = "walletEntity")
  HistoryTransactionEntity map(HistoryTransaction historyTransaction, WalletEntity walletEntity);
}
