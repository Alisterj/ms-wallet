package com.kdaria.ms_wallet.domain.port;

import com.kdaria.ms_wallet.domain.model.Wallet;
import com.kdaria.ms_wallet.presistence.entity.WalletEntity;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public interface WalletProvider {

  Optional<Wallet> findWalletById(@NotNull UUID uuid);

  void save(@NotNull Wallet wallet);
}
