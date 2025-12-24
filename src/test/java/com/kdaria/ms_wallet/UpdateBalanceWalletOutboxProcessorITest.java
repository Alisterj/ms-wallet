package com.kdaria.ms_wallet;

import com.kdaria.ms_wallet.adapter.outbox.UpdateBalanceWalletOutboxProcessor;
import com.kdaria.ms_wallet.domain.model.*;
import com.kdaria.ms_wallet.presistence.entity.*;
import com.kdaria.ms_wallet.presistence.repository.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@Sql(scripts = "/clear_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
public class UpdateBalanceWalletOutboxProcessorITest {
  @Autowired
  private UpdateBalanceWalletOutboxProcessor processor;

  @Autowired
  private OperationWalletRepository operationRepository;

  @Autowired
  private WalletRepository walletRepository;

  @Test
  @Sql(scripts = "/udate_balance.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @DisplayName("Должен обработать все записи со статусом processed = FALSE из SQL файла")
  void shouldProcessPendingOperationsFromSql() {
    long initialPendingCount = operationRepository.findAll().stream()
      .filter(op -> !op.getProcessed())
      .count();
    assert initialPendingCount == 2;

    processor.processPendingEvent();

    List<OperationWalletEntity> allOperations = operationRepository.findAll();
    boolean allProcessed = allOperations.stream().allMatch(OperationWalletEntity::getProcessed);

    assertThat(allProcessed).isTrue();

    WalletEntity wallet = walletRepository.findById(UUID.fromString("a1b2c3d4-e5f6-4a5b-8c7d-9e0f1a2b3c4d")).orElseThrow();
    assertThat(wallet.getBalance()).isEqualByComparingTo("100.00");
  }
}
