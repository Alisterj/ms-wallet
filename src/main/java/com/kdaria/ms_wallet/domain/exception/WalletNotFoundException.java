package com.kdaria.ms_wallet.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WalletNotFoundException extends RuntimeException {

  public WalletNotFoundException(String message, UUID walletId) {
    super(message + " " + walletId);
  }
}
