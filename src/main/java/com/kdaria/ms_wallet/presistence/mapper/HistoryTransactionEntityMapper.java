package com.kdaria.ms_wallet.presistence.mapper;

import com.kdaria.ms_wallet.domain.model.HistoryTransaction;
import com.kdaria.ms_wallet.presistence.entity.HistoryTransactionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HistoryTransactionEntityMapper {

  HistoryTransactionEntity map(HistoryTransaction historyTransaction);
}
