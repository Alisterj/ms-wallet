package com.kdaria.ms_wallet.api.dto;

import com.kdaria.ms_wallet.api.OperationType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.UUID;

@Schema(description = "Запрос на изменение суммы кошелька")
public record ChangeWalletRequest(@Schema(description = "ID кошелька", example = "cf4847df-ac06-47a2-8bf7-4888cd2773a0") @NotNull UUID walletId,
                                  @Schema(description = "Тип операции", example = "DEPOSIT") @NotNull OperationType operationType,
                                  @Schema(description = "Сумма", example = "100") @NotNull @Positive BigDecimal amount) {}
