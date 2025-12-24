package com.kdaria.ms_wallet.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InsufficientFundsException extends RuntimeException {

  public InsufficientFundsException(String message) {}
}
