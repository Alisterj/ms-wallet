package com.kdaria.ms_wallet.adapter.api.mapper;

import com.kdaria.ms_wallet.domain.command.ChangeWalletCommand;
import com.kdaria.ms_wallet.adapter.api.dto.ChangeWalletRequest;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface WalletControllerMapper {

  ChangeWalletCommand map(@NotNull ChangeWalletRequest request);
}
