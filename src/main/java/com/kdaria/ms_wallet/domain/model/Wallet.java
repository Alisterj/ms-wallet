package com.kdaria.ms_wallet.domain.model;

import com.kdaria.ms_wallet.enums.OperationType;
import lombok.*;
import org.jetbrains.annotations.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class Wallet {

  @NotNull
  private UUID walletId;

  @Nullable
  private OperationType operationType;

  @NotNull
  private BigDecimal balance;
}
