package com.kdaria.ms_wallet.adapter.api.mapper;

import com.kdaria.ms_wallet.domain.command.UpdateBalanceWalletCommand;
import com.kdaria.ms_wallet.adapter.api.dto.UpdateBalanceWalletRequest;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface WalletControllerMapper {

  UpdateBalanceWalletCommand map(@NotNull UpdateBalanceWalletRequest request);
}
