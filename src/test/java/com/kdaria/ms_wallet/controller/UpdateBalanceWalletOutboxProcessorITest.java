package com.kdaria.ms_wallet.controller;

import com.kdaria.ms_wallet.adapter.outbox.UpdateBalanceWalletOutboxProcessor;
import com.kdaria.ms_wallet.config.AppTest;
import com.kdaria.ms_wallet.persistence.entity.*;
import com.kdaria.ms_wallet.persistence.repository.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@AppTest
@Sql(scripts = {"/clear_tables.sql", "/update_balance.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class UpdateBalanceWalletOutboxProcessorITest {
  @Autowired
  private UpdateBalanceWalletOutboxProcessor processor;

  @Autowired
  private OperationWalletRepository operationRepository;
  @Autowired
  private WalletRepository walletRepository;
  @Autowired
  private HistoryTransactionRepository historyTransactionRepository;

  @Test
  void shouldProcessPendingOperationsFromSql() {
    processor.processPendingEvent();

    assertThat(historyTransactionRepository.findAll().size()).isEqualTo(4);
    assertThat(operationRepository.findAll().size()).isEqualTo(0);

    WalletEntity walletEntity = walletRepository.findById(UUID.fromString("550e8400-e29b-41d4-a716-446655440000")).orElseThrow();
    assertThat(walletEntity.getBalance()).isEqualByComparingTo("200.00");

    walletEntity =  walletRepository.findById(UUID.fromString("7f3b6c21-1b3d-4c5e-8f9a-1234567890ab")).orElseThrow();
    assertThat(walletEntity.getBalance()).isEqualByComparingTo("3900.50");
  }
}
