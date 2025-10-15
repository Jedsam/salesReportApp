-- Set the database name
CREATE DATABASE IF NOT EXISTS `payment_system`;
USE `payment_system`;

-- Define the custom ENUM types for statuses and payment types.
-- MariaDB doesn't have a direct UUID type; we'll use CHAR(36) for UUIDs.
-- The DECIMAL(10, 2) and DECIMAL(4, 2) types will be used for money and rates.

-- Table: MERCHANTS
CREATE TABLE `MERCHANTS` (
    `merchant_id`   CHAR(36)        NOT NULL PRIMARY KEY, -- UUID
    `name`          VARCHAR(255)    NOT NULL,
    `business_name` VARCHAR(255)    NOT NULL,
    `email`         VARCHAR(255)    NOT NULL UNIQUE COMMENT 'Login information',
    `password_hash` CHAR(64)        NOT NULL COMMENT 'hash + salt (assuming SHA-256 hash output)',
    `phone`         VARCHAR(30)     COMMENT 'Contact information',
    `address`       VARCHAR(255)    COMMENT 'Contact address',
    `created_at`    TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `status`        ENUM('active', 'suspended', 'closed') NOT NULL DEFAULT 'active'
);

---

-- Table: SHOPS
CREATE TABLE `SHOPS` (
    `shop_id`       CHAR(36)        NOT NULL PRIMARY KEY, -- UUID
    `merchant_id`   CHAR(36)        NOT NULL,
    `name`          VARCHAR(255)    NOT NULL,
    `address`       VARCHAR(255)    NOT NULL,
    `phone`         VARCHAR(30),
    `email`         VARCHAR(255),
    `created_at`    TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `status`        ENUM('active', 'suspended', 'closed') NOT NULL DEFAULT 'active',
    FOREIGN KEY (`merchant_id`) REFERENCES `MERCHANTS`(`merchant_id`)
        ON DELETE CASCADE -- If a merchant is deleted, their shops are also deleted
);

---

-- Table: FIRMWARE (Note: The schema has no FK from FIRMWARE to DEVICES, only from DEVICES to FIRMWARE)
CREATE TABLE `FIRMWARE` (
    `firmware_id`   CHAR(36)        NOT NULL PRIMARY KEY, -- UUID
    `firmware_version` VARCHAR(50)  NOT NULL UNIQUE
);

---

-- Table: DEVICES
CREATE TABLE `DEVICES` (
    `device_id`     CHAR(36)        NOT NULL PRIMARY KEY, -- UUID
    `shop_id`       CHAR(36)        NOT NULL,
    `firmware_id`   CHAR(36)        NOT NULL, -- Assuming FIRMWARE has a UUID
    `model`         VARCHAR(100)    NOT NULL,
    `last_seen`     TIMESTAMP       NULL,
    `created_at`    TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `status`        ENUM('active', 'suspended', 'closed') NOT NULL DEFAULT 'active',
    FOREIGN KEY (`shop_id`) REFERENCES `SHOPS`(`shop_id`)
        ON DELETE RESTRICT,
    FOREIGN KEY (`firmware_id`) REFERENCES `FIRMWARE`(`firmware_id`)
        ON DELETE RESTRICT
);

---

-- Table: PRODUCTS
CREATE TABLE `PRODUCTS` (
    `product_id`    CHAR(36)        NOT NULL PRIMARY KEY, -- UUID
    `name`          VARCHAR(255)    NOT NULL,
    `price`         DECIMAL(10, 2)  NOT NULL,
    `vat_rate`      DECIMAL(4, 2)   NOT NULL, -- DECIMAL4d2 from schema
    `created`       TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP
);

---

-- Base Table for Polymorphic Payment Methods
-- All payment methods will have their own table which links back to a generic ID.
CREATE TABLE `PAYMENT_METHODS` (
    `payment_method_id` CHAR(36)    NOT NULL PRIMARY KEY -- UUID, serves as the parent ID
);

---

-- Table: CASH_PAYMENT_METHOD (Inherits from PAYMENT_METHODS)
CREATE TABLE `CASH_PAYMENT_METHOD` (
    `payment_method_id` CHAR(36)        NOT NULL PRIMARY KEY, -- FK to PAYMENT_METHODS
    `received_amount`   DECIMAL(10, 2)  NOT NULL,
    `change_given`      DECIMAL(10, 2)  NOT NULL,
    FOREIGN KEY (`payment_method_id`) REFERENCES `PAYMENT_METHODS`(`payment_method_id`)
        ON DELETE CASCADE
);

---

-- Table: COUPON_PAYMENT_METHOD (Inherits from PAYMENT_METHODS)
CREATE TABLE `COUPON_PAYMENT_METHOD` (
    `payment_method_id` CHAR(36)        NOT NULL PRIMARY KEY, -- FK to PAYMENT_METHODS
    `coupon_code`       VARCHAR(50)     NOT NULL,
    `coupon_value`      DECIMAL(10, 2)  NOT NULL,
    `expiry_date`       DATE            NOT NULL,
    FOREIGN KEY (`payment_method_id`) REFERENCES `PAYMENT_METHODS`(`payment_method_id`)
        ON DELETE CASCADE
);

---

-- Table: CREDIT_PAYMENT_METHOD (Inherits from PAYMENT_METHODS)
CREATE TABLE `CREDIT_PAYMENT_METHOD` (
    `payment_method_id` CHAR(36)        NOT NULL PRIMARY KEY, -- FK to PAYMENT_METHODS
    `card_scheme`       VARCHAR(50)     NOT NULL,
    `card_last4`        CHAR(4)         NOT NULL,
    `auth_code`         VARCHAR(20)     NOT NULL,
    FOREIGN KEY (`payment_method_id`) REFERENCES `PAYMENT_METHODS`(`payment_method_id`)
        ON DELETE CASCADE
);

---

-- Table: TRANSACTIONS
CREATE TABLE `TRANSACTIONS` (
    `transaction_id`    CHAR(36)        NOT NULL PRIMARY KEY, -- UUID
    `device_id`         CHAR(36)        NOT NULL, -- Added based on DEVICES ||--o{ TRANSACTIONS : madeat
    `payment_method_id` CHAR(36)        NULL, -- It's an optional relationship in the diagram, though usually required
    `subtotal`          DECIMAL(10, 2)  NOT NULL,
    `total`             DECIMAL(10, 2)  NOT NULL,
    `currency`          CHAR(3)         NOT NULL, -- CHAR3 from schema
    `auth_code`         VARCHAR(20),
    `created_at`        TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `status`            ENUM('active', 'suspended', 'closed') NOT NULL DEFAULT 'active',
    `payment_type`      ENUM('cash', 'credit', 'coupon') NOT NULL,
    FOREIGN KEY (`device_id`) REFERENCES `DEVICES`(`device_id`)
        ON DELETE RESTRICT,
    FOREIGN KEY (`payment_method_id`) REFERENCES `PAYMENT_METHODS`(`payment_method_id`)
        ON DELETE RESTRICT
);

---

-- Table: TRANSACTION_ITEMS
CREATE TABLE `TRANSACTION_ITEMS` (
    `transaction_item_id` CHAR(36)        NOT NULL PRIMARY KEY, -- UUID
    `transaction_id`      CHAR(36)        NOT NULL,
    `product_id`          CHAR(36)        NOT NULL,
    `product_name`        VARCHAR(255)    NOT NULL,
    `sku`                 VARCHAR(20)     NOT NULL, -- VARCHAR20 from schema
    `unit_price`          DECIMAL(10, 2)  NOT NULL,
    `quantity`            DECIMAL(10, 2)  NOT NULL,
    `vat_rate`            DECIMAL(10, 2)  NOT NULL,
    `total`               DECIMAL(10, 2)  NOT NULL,
    FOREIGN KEY (`transaction_id`) REFERENCES `TRANSACTIONS`(`transaction_id`)
        ON DELETE CASCADE, -- If a transaction is deleted, its items are deleted
    FOREIGN KEY (`product_id`) REFERENCES `PRODUCTS`(`product_id`)
        ON DELETE RESTRICT
);
