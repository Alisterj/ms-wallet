package com.kdaria.ms_wallet.domain.model;

import com.kdaria.ms_wallet.en.OperationType;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
public class OperationWallet {

  @Nullable
  private Long id;

  @NotNull
  private UUID walletId;

  @NotNull
  private BigDecimal sum;

  @NotNull
  private OperationType type;

  @NotNull
  private LocalDateTime createdDate;

  @NotNull
  private Boolean processed;
}
