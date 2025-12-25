package com.kdaria.ms_wallet.domain.port;

import com.kdaria.ms_wallet.domain.model.*;
import com.kdaria.ms_wallet.enums.OperationType;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.*;

public interface WalletProvider {

  Optional<Wallet> findWalletById(@NotNull UUID uuid);

  void updateBalance(@NotNull Wallet wallet, @NotNull OperationType operationType, @NotNull BigDecimal amount);
}
