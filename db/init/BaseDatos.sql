-- ========================
-- SCHEMA: microservicios_db
-- ========================

-- Tabla Person
CREATE TABLE person (
    "id" SERIAL PRIMARY KEY,
    "name" VARCHAR(100) NOT NULL,
    "gender" VARCHAR(10),
    "age" INT,
    "identification" VARCHAR(20) UNIQUE NOT NULL,
    "address" VARCHAR(200),
    "phone" VARCHAR(20)
);

-- Tabla Customer (hereda de Person por composición)
CREATE TABLE customer (
    "id" INT  PRIMARY KEY,
	"customer_id" VARCHAR(100) UNIQUE NOT NULL,
    "password" VARCHAR(100) NOT NULL,
    "state" BOOLEAN NOT NULL,
    CONSTRAINT fk_customer_person FOREIGN KEY (id) REFERENCES person(id)
);

-- Tabla Account
CREATE TABLE account (
    "id" SERIAL PRIMARY KEY,
    "account_number" VARCHAR(20) UNIQUE NOT NULL,
    "account_type" VARCHAR(50) NOT NULL,
    "initial_balance" DECIMAL(12,2) NOT NULLL DEFAULT 0,
	"available_balance" DECIMAL(12,2) NOT NULLL DEFAULT 0,
    "state" BOOLEAN NOT NULL DEFAULT TRUE,
    "customer_id" VARCHAR(100) NOT NULL,
    CONSTRAINT fk_account_customer FOREIGN KEY (customer_id) REFERENCES customer(id)
);

-- Tabla Movement
CREATE TABLE movement (
    "id" SERIAL PRIMARY KEY,
    "date" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    "movement_type" VARCHAR(50) NOT NULL,
    "amount" DECIMAL(12,2) NOT NULL,
    "balance" DECIMAL(12,2) NOT NULL,
    "account_id" INT NOT NULL,
    CONSTRAINT fk_movement_account FOREIGN KEY (account_id) REFERENCES account(id)
);
