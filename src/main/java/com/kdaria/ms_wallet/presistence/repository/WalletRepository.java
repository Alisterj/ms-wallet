package com.kdaria.ms_wallet.presistence.repository;

import com.kdaria.ms_wallet.presistence.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletRepository extends JpaRepository<WalletEntity, UUID> {}
