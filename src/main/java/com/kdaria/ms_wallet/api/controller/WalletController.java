package com.kdaria.ms_wallet.api.controller;

import com.kdaria.ms_wallet.api.dto.ChangeWalletRequest;
import com.kdaria.ms_wallet.api.mapper.WalletControllerMapper;
import com.kdaria.ms_wallet.domain.command.ChangeWalletCommand;
import com.kdaria.ms_wallet.domain.usecase.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "API для работы с кошлельком")
public class WalletController {
  private final ChangeWalletUseCase changeWalletUseCase;
  private final GetWalletBalanceUseCase getWalletBalanceUseCase;
  private final WalletControllerMapper mapper;

  @PostMapping("/wallet")
  @Operation(summary = "Метод изменения счета")
  @ApiResponse(responseCode = "200", description = "Успех")
  @ApiResponse(responseCode = "404", description = "Правило не найдено", content = @Content(schema = @Schema))
  @ApiResponse(responseCode = "400", description = "Некорректные данные", content = @Content(schema = @Schema))
  @ApiResponse(responseCode = "500", description = "Ошибка выполнения операции", content = @Content(schema = @Schema))
  public ResponseEntity<Void> changeWallet(@Valid @NotNull ChangeWalletRequest request) {
    ChangeWalletCommand command = mapper.map(request);
    changeWalletUseCase.change(command);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/wallets/{walletId}")
  @Operation(summary = "Метод получения данных по счету")
  @ApiResponse(responseCode = "200", description = "Успех")
  @ApiResponse(responseCode = "404", description = "Правило не найдено", content = @Content(schema = @Schema))
  @ApiResponse(responseCode = "400", description = "Некорректные данные", content = @Content(schema = @Schema))
  @ApiResponse(responseCode = "500", description = "Ошибка выполнения операции", content = @Content(schema = @Schema))
  public BigDecimal getWallet(@PathVariable UUID walletId) {
    return getWalletBalanceUseCase.getBalance(walletId);
  }
}
