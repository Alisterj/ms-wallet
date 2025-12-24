package com.kdaria.ms_wallet;

import com.kdaria.ms_wallet.en.OperationType;
import com.kdaria.ms_wallet.presistence.entity.*;
import com.kdaria.ms_wallet.presistence.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"/clear_tables.sql",
                "/get_wallet.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class WalletControllerITest {
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private OperationWalletRepository operationWalletRepository;

  @Test
  void When_UpdateWalletDeposit_Expect_Success() throws Exception {
    UUID walletId = UUID.fromString("7f3b6c21-1b3d-4c5e-8f9a-1234567890ab");
    mockMvc.perform(post("/api/v1/wallet")
        .param("walletId", walletId.toString())
        .param("operationType", OperationType.DEPOSIT.toString())
        .param("amount", "20"))
      .andExpectAll(
        status().isOk()
      );

    OperationWalletEntity entity = operationWalletRepository.findAll()
      .stream().filter(e -> e.getWallet().getId().equals(walletId)).findFirst().get();
    assertThat(entity.getSum())
      .as("Заявка должна иметь сумму %s", 20)
      .isEqualByComparingTo(new BigDecimal("20.00"));
  }

  @Test
  void When_UpdateWalletWithdraw_Expect_Success() throws Exception {
    UUID walletId = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
    mockMvc.perform(post("/api/v1/wallet")
        .param("walletId", walletId.toString())
        .param("operationType", OperationType.WITHDRAW.toString())
        .param("amount", "100"))
      .andExpectAll(
        status().isOk()
      );

    OperationWalletEntity entity = operationWalletRepository.findAll()
      .stream().filter(e -> e.getWallet().getId().equals(walletId)).findFirst().get();
    assertThat(entity.getSum())
      .as("Заявка должна иметь сумму %s", 100)
      .isEqualByComparingTo(new BigDecimal("100.00"));
  }

  @Test
  void When_UpdateWalletWithNotExistentWalletId_Expect_BadRequest() throws Exception {
    mockMvc.perform(post("/api/v1/wallet")
        .param("walletId", "9f3b6c21-1b3d-4c5e-8f9a-1234567890ab")
        .param("operationType", OperationType.DEPOSIT.toString())
        .param("amount", "20"))
      .andExpectAll(
        status().isNotFound()
      );
  }

  @Test
  void When_UpdateWalletWithTooMuchOutput_Expect_BadRequest() throws Exception {
    mockMvc.perform(post("/api/v1/wallet")
        .param("walletId", "7f3b6c21-1b3d-4c5e-8f9a-1234567890ab")
        .param("operationType", OperationType.WITHDRAW.toString())
        .param("amount", "5000000"))
      .andExpectAll(
        status().isForbidden()
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
