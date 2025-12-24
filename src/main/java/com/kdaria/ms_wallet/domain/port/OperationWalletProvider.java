package com.kdaria.ms_wallet.domain.port;

import com.kdaria.ms_wallet.domain.model.OperationWallet;
import org.jetbrains.annotations.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OperationWalletProvider {

  void save(@NotNull OperationWallet operationWallet);

  List<OperationWallet> findAllByCreatedDate(Pageable pageable);

  void delete(@NotNull Long id);
}
