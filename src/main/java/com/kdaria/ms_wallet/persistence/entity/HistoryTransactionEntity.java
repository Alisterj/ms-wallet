package com.kdaria.ms_wallet.persistence.entity;

import com.kdaria.ms_wallet.enums.OperationType;
import jakarta.persistence.*;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "history_transaction")
public class HistoryTransactionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @JoinColumn(name = "wallet_id", nullable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  private WalletEntity wallet;

  @NotNull
  private BigDecimal sum;

  @NotNull
  @Enumerated(EnumType.STRING)
  private OperationType type;

  @NotNull
  @Column(name = "created_date")
  private LocalDateTime createdDate;
}
