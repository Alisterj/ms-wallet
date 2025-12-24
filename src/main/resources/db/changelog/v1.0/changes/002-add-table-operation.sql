ALTER TABLE wallet
    ALTER COLUMN id SET NOT NULL;

CREATE TABLE operation_wallet(
    id           BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    wallet_id    UUID NOT NULL,
    sum          DECIMAL(19,2) NOT NULL,
    type         VARCHAR(20) NOT NULL,
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    processed    BOOLEAN NOT NULL DEFAULT FALSE
);

COMMENT ON TABLE operation_wallet IS 'Таблица для хранения информации об операциях';
COMMENT ON COLUMN operation_wallet.id IS 'Идентификатор записи';
COMMENT ON COLUMN operation_wallet.wallet_id IS 'Идентификатор кошелька';
COMMENT ON COLUMN operation_wallet.sum IS 'Сумма операции';
COMMENT ON COLUMN operation_wallet.type IS 'Тип операции';
COMMENT ON COLUMN operation_wallet.created_date IS 'Дата операции';

COMMENT ON TABLE wallet IS 'Таблица для хранения информации о кошельке';
COMMENT ON COLUMN wallet.id IS 'Идентификатор записи';
COMMENT ON COLUMN wallet.balance IS 'Баланс кошелька';

CREATE TABLE history_transaction(
    id           BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    wallet_id    UUID NOT NULL,
    sum          DECIMAL(19,2) NOT NULL,
    type         VARCHAR(20) NOT NULL,
    created_date TIMESTAMP NOT NULL
);

COMMENT ON TABLE history_transaction IS 'Таблица хранения истории транзакций';
COMMENT ON COLUMN history_transaction.id IS 'Идентификатор записи';
COMMENT ON COLUMN history_transaction.wallet_id IS 'Идентификатор кошелька';
COMMENT ON COLUMN history_transaction.sum IS 'Сумма операции';
COMMENT ON COLUMN history_transaction.type IS 'Тип операции';
COMMENT ON COLUMN history_transaction.created_date IS 'Дата операции';
