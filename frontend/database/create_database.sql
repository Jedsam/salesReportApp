PRAGMA foreign_keys = ON;

-- USERS
CREATE TABLE IF NOT EXISTS USERS (
    user_id        BLOB NOT NULL PRIMARY KEY,
    email          TEXT NOT NULL UNIQUE,
    password_hash  TEXT NOT NULL
);

-- MERCHANTS
CREATE TABLE IF NOT EXISTS MERCHANTS (
    merchant_id    BLOB NOT NULL PRIMARY KEY,
    user_id        BLOB NOT NULL,
    name           TEXT NOT NULL,
    business_name  TEXT NOT NULL,
    phone          TEXT,
    address        TEXT,
    created_at     TEXT NOT NULL,
    status         TEXT NOT NULL DEFAULT 'active'
        CHECK(status IN ('active', 'suspended', 'closed')),
    FOREIGN KEY(user_id) REFERENCES USERS(user_id) 
);

-- SHOPS
CREATE TABLE IF NOT EXISTS SHOPS (
    shop_id       BLOB NOT NULL PRIMARY KEY,
    merchant_id   BLOB NOT NULL,
    name          TEXT NOT NULL,
    address       TEXT NOT NULL,
    phone         TEXT,
    email         TEXT,
    created_at    TEXT NOT NULL,
    status        TEXT NOT NULL DEFAULT 'active'
        CHECK(status IN ('active', 'suspended', 'closed')),
    FOREIGN KEY(merchant_id) REFERENCES MERCHANTS(merchant_id) 
);

-- FIRMWARE
CREATE TABLE IF NOT EXISTS FIRMWARE (
    firmware_id      BLOB NOT NULL PRIMARY KEY,
    firmware_version TEXT NOT NULL UNIQUE
);

-- MODEL
CREATE TABLE IF NOT EXISTS MODEL (
    model_id   BLOB NOT NULL PRIMARY KEY,
    model_name TEXT NOT NULL UNIQUE
);

-- DEVICES
CREATE TABLE IF NOT EXISTS DEVICES (
    device_id    BLOB NOT NULL PRIMARY KEY,
    shop_id      BLOB NOT NULL,
    firmware_id  BLOB NOT NULL,
    model_id     BLOB NOT NULL,
    last_seen    TEXT,
    created_at   TEXT NOT NULL,
    status       TEXT NOT NULL DEFAULT 'active'
        CHECK(status IN ('active', 'suspended', 'closed')),
    FOREIGN KEY(shop_id) REFERENCES SHOPS(shop_id) ,
    FOREIGN KEY(firmware_id) REFERENCES FIRMWARE(firmware_id) ,
    FOREIGN KEY(model_id) REFERENCES MODEL(model_id) 
);

-- PRODUCTS
CREATE TABLE IF NOT EXISTS PRODUCTS (
    product_id  BLOB NOT NULL PRIMARY KEY,
    name        TEXT NOT NULL,
    price       REAL NOT NULL,
    vat_rate    REAL NOT NULL,
    is_deleted  INTEGER NOT NULL DEFAULT 0,
    created     TEXT NOT NULL
);

-- TRANSACTIONS
CREATE TABLE IF NOT EXISTS TRANSACTIONS (
    transaction_id  BLOB NOT NULL PRIMARY KEY,
    device_id       BLOB NOT NULL,
    subtotal        REAL NOT NULL,
    total           REAL NOT NULL,
    currency        TEXT NOT NULL,
    auth_code       TEXT,
    created_at      TEXT NOT NULL,
    status          TEXT NOT NULL DEFAULT 'active'
        CHECK(status IN ('active', 'suspended', 'closed')),
    payment_type    TEXT NOT NULL
        CHECK(payment_type IN ('cash', 'credit', 'coupon')),
    FOREIGN KEY(device_id) REFERENCES DEVICES(device_id) 
);

-- CASH PAYMENT
CREATE TABLE IF NOT EXISTS CASH_PAYMENT_METHOD (
    transaction_id  BLOB NOT NULL PRIMARY KEY,
    received_amount REAL NOT NULL,
    change_given    REAL NOT NULL,
    FOREIGN KEY(transaction_id) REFERENCES TRANSACTIONS(transaction_id) 
);

-- COUPON PAYMENT
CREATE TABLE IF NOT EXISTS COUPON_PAYMENT_METHOD (
    transaction_id  BLOB NOT NULL PRIMARY KEY,
    coupon_code     TEXT NOT NULL,
    coupon_value    REAL NOT NULL,
    expiry_date     TEXT NOT NULL,
    FOREIGN KEY(transaction_id) REFERENCES TRANSACTIONS(transaction_id) 
);

-- CREDIT PAYMENT
CREATE TABLE IF NOT EXISTS CREDIT_PAYMENT_METHOD (
    transaction_id  BLOB NOT NULL PRIMARY KEY,
    card_scheme     TEXT NOT NULL,
    card_last4      TEXT NOT NULL,
    auth_code       TEXT NOT NULL,
    FOREIGN KEY(transaction_id) REFERENCES TRANSACTIONS(transaction_id) 
);

-- TRANSACTION ITEMS
CREATE TABLE IF NOT EXISTS TRANSACTION_ITEMS (
    transaction_item_id  BLOB NOT NULL PRIMARY KEY,
    transaction_id       BLOB NOT NULL,
    product_id           BLOB NOT NULL,
    product_name         TEXT NOT NULL,
    unit_price           REAL NOT NULL,
    quantity             REAL NOT NULL,
    vat_rate             REAL NOT NULL,
    total                REAL NOT NULL,
    FOREIGN KEY(transaction_id) REFERENCES TRANSACTIONS(transaction_id) ,
    FOREIGN KEY(product_id) REFERENCES PRODUCTS(product_id) 
);

