package com.kdaria.ms_wallet.presistence.entity;

import com.kdaria.ms_wallet.en.OperationType;
import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "operation_wallet")
public class OperationWalletEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(name = "wallet_id")
  private UUID walletId;

  @NotNull
  private BigDecimal sum;

  @NotNull
  @Enumerated(EnumType.STRING)
  private OperationType type;

  @NotNull
  @Column(name = "created_date")
  private LocalDateTime createdDate;

  @NotNull
  private Boolean processed;
}
