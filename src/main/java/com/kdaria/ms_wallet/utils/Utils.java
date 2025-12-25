package com.kdaria.ms_wallet.utils;

import com.kdaria.ms_wallet.enums.OperationType;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class Utils {

  public boolean invalidWithdrawal(OperationType operationType, BigDecimal balance, BigDecimal amount) {
    return operationType == OperationType.WITHDRAW && balance.compareTo(amount) < 0;
  }

}
