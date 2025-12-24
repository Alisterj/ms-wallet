package com.kdaria.ms_wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MsWalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsWalletApplication.class, args);
	}
}
