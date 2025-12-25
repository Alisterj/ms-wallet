package com.kdaria.ms_wallet.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "wallet")
@AllArgsConstructor
@NoArgsConstructor
public class WalletEntity {

  @Id
  @NotNull
  private UUID id;

  @NotNull
  private BigDecimal balance;
}
