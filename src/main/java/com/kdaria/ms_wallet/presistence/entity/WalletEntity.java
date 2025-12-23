package com.kdaria.ms_wallet.presistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Entity
@Setter
@Table(name = "wallet")
public class WalletEntity {
  @Id
  private UUID id;
  private BigDecimal balance;
}
