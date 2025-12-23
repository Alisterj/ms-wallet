package com.kdaria.ms_wallet.domain.model;

import com.kdaria.ms_wallet.adapter.api.OperationType;
import com.kdaria.ms_wallet.domain.exception.InsufficientFundsException;
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
  private BigDecimal amount;

  public void deposit(BigDecimal amount) {
    this.amount = this.amount.add(amount);
  }

  public void withdraw(BigDecimal amount) {
    if (this.amount.compareTo(amount) < 0) throw new InsufficientFundsException();
    this.amount = this.amount.subtract(amount);
  }
}
