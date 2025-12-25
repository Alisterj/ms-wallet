package com.kdaria.ms_wallet.domain.command;

import com.kdaria.ms_wallet.enums.OperationType;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateBalanceWalletCommand(@NotNull UUID walletId,
                                         @NotNull OperationType operationType,
                                         @NotNull BigDecimal amount) {}
