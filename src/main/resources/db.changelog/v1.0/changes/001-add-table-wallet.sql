CREATE TABLE wallet(
    id      UUID PRIMARY KEY,
    balance DECIMAL(10,2) NOT NULL DEFAULT 0 CHECK (balance >= 0)
);
