package com.kdaria.ms_wallet.domain.port;

import com.kdaria.ms_wallet.domain.model.*;
import com.kdaria.ms_wallet.en.OperationType;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.*;

public interface WalletProvider {

  Optional<Wallet> findWalletById(@NotNull UUID uuid);

  void checkWallet(@NotNull UUID uuid, @NotNull BigDecimal amount);

  void updateBalance(@NotNull UUID uuid, @NotNull OperationType operationType, @NotNull BigDecimal amount);

}
