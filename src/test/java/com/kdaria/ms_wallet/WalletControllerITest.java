package com.kdaria.ms_wallet;

import com.kdaria.ms_wallet.api.OperationType;
import com.kdaria.ms_wallet.presistence.entity.WalletEntity;
import com.kdaria.ms_wallet.presistence.repository.WalletRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/get_wallet.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts = "/clear_table.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
public class WalletControllerITest {
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private WalletRepository walletRepository;

  @Test
  void When_ChangeWalletDeposit_Expect_Success() throws Exception {
    mockMvc.perform(post("/api/v1/wallet")
        .param("walletId", "7f3b6c21-1b3d-4c5e-8f9a-1234567890ab")
        .param("operationType", OperationType.DEPOSIT.toString())
        .param("amount", "20"))
      .andExpectAll(
        status().isOk()
      );

    WalletEntity entity = walletRepository.findById(UUID.fromString("7f3b6c21-1b3d-4c5e-8f9a-1234567890ab")).get();
    assertThat(entity.getBalance())
      .as("Баланс кошелька должен увеличиться на %s", 20)
      .isEqualByComparingTo(new BigDecimal("2520.50"));
  }

  @Test
  void When_ChangeWalletWithdraw_Expect_Success() throws Exception {
    mockMvc.perform(post("/api/v1/wallet")
        .param("walletId", "550e8400-e29b-41d4-a716-446655440000")
        .param("operationType", OperationType.WITHDRAW.toString())
        .param("amount", "100"))
      .andExpectAll(
        status().isOk()
      );

    WalletEntity entity = walletRepository.findById(UUID.fromString("550e8400-e29b-41d4-a716-446655440000")).get();
    assertThat(entity.getBalance())
      .as("Баланс кошелька должен уменьгиться на %s", 100)
      .isEqualByComparingTo(new BigDecimal("50.00"));
  }

  @Test
  void When_ChangeWalletWithNotExistentWalletId_Expect_BadRequest() throws Exception {
    mockMvc.perform(post("/api/v1/wallet")
        .param("walletId", "9f3b6c21-1b3d-4c5e-8f9a-1234567890ab")
        .param("operationType", OperationType.DEPOSIT.toString())
        .param("amount", "20"))
      .andExpectAll(
        status().isNotFound()
      );
  }

  @Test
  void When_ChangeWalletWithTooMuchOutput_Expect_BadRequest() throws Exception {
    mockMvc.perform(post("/api/v1/wallet")
        .param("walletId", "7f3b6c21-1b3d-4c5e-8f9a-1234567890ab")
        .param("operationType", OperationType.WITHDRAW.toString())
        .param("amount", "5000000"))
      .andExpectAll(
        status().isUnprocessableContent()
      );
  }

  @Test
  void When_GetWallet_Expect_Success() throws Exception {
    mockMvc.perform(get("/api/v1/wallets/bc8e624b-3d02-4751-879e-4a69e7107755"))
      .andExpectAll(
        status().isOk(),
        jsonPath("$").value(10.99)
      );
  }

  @Test
  void When_GetWalletWithNotExistentWalletId_Expect_BadRequest() throws Exception {
    mockMvc.perform(get("/api/v1/wallets/bc8e624b-3d02-4751-879e-4a69e7107755"))
      .andExpectAll(
        status().isOk(),
        jsonPath("$").value(10.99)
      );
  }
}
