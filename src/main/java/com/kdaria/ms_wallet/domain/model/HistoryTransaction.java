package com.kdaria.ms_wallet.domain.model;

import com.kdaria.ms_wallet.enums.OperationType;
import lombok.*;
import org.jetbrains.annotations.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class HistoryTransaction {

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
}
