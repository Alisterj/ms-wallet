package com.kdaria.ms_wallet.config;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.extension.Extension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@Log4j2
public class PostgreSQLExtension implements Extension {

  private static final int PORT = 5432;
  private static final String DATABASE = "ms_wallet";
  private static final String USERNAME = "ms_wallet";
  private static final String PASSWORD = "ms_wallet";

  static {
    var container = new PostgreSQLContainer<>(DockerImageName.parse("postgres:16.2")).withLogConsumer((outputFrame) -> log.info(
        outputFrame.getUtf8String()))
      .withExposedPorts(PORT)
      .withDatabaseName(DATABASE)
      .withUsername(USERNAME)
      .withPassword(PASSWORD);

    container.start();

    System.setProperty("config.postgres.host", container.getHost() + ":" + container.getMappedPort(PORT));
    System.setProperty("config.postgres.schema", DATABASE);
    System.setProperty("config.postgres.username", USERNAME);
    System.setProperty("config.postgres.password", PASSWORD);

    log.info("PostgreSQL started: {}, {}:{}", container.getJdbcUrl(), USERNAME, PASSWORD);
  }
}
