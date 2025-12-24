package com.kdaria.ms_wallet.domain.port;

import com.kdaria.ms_wallet.domain.model.OperationWallet;
import org.jetbrains.annotations.NotNull;

public interface OperationWalletProvider {

  void save(@NotNull OperationWallet operationWallet);
}
