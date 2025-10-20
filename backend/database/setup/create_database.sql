-- Set the database name
CREATE DATABASE IF NOT EXISTS `payment_system`;
USE `payment_system`;

-- Table: MERCHANTS
CREATE TABLE IF NOT EXISTS `MERCHANTS` (
    `merchant_id`   BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
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
CREATE TABLE IF NOT EXISTS `SHOPS` (
    `shop_id`       BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `merchant_id`   BIGINT UNSIGNED NOT NULL,
    `name`          VARCHAR(255)    NOT NULL,
    `address`       VARCHAR(255)    NOT NULL,
    `phone`         VARCHAR(30),
    `email`         VARCHAR(255),
    `created_at`    TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `status`        ENUM('active', 'suspended', 'closed') NOT NULL DEFAULT 'active',
    FOREIGN KEY (`merchant_id`) REFERENCES `MERCHANTS`(`merchant_id`)
        ON DELETE CASCADE
);

---

-- Table: FIRMWARE
CREATE TABLE IF NOT EXISTS `FIRMWARE` (
    `firmware_id`      BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `firmware_version` VARCHAR(50) NOT NULL UNIQUE
);

-- Table: MODEL
CREATE TABLE IF NOT EXISTS `MODEL` (
    `model_id`      BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `model_name` VARCHAR(50) NOT NULL UNIQUE
);

---

-- Table: DEVICES
CREATE TABLE IF NOT EXISTS `DEVICES` (
    `device_id`     BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `shop_id`       BIGINT UNSIGNED NOT NULL,
    `firmware_id`   BIGINT UNSIGNED NOT NULL,
    `model_id`   BIGINT UNSIGNED NOT NULL,
    `last_seen`     TIMESTAMP       NULL,
    `created_at`    TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `status`        ENUM('active', 'suspended', 'closed') NOT NULL DEFAULT 'active',
    FOREIGN KEY (`shop_id`) REFERENCES `SHOPS`(`shop_id`)
        ON DELETE RESTRICT,
    FOREIGN KEY (`firmware_id`) REFERENCES `FIRMWARE`(`firmware_id`)
        ON DELETE RESTRICT,
    FOREIGN KEY (`model_id`) REFERENCES `MODEL`(`model_id`)
        ON DELETE RESTRICT
);

---

-- Table: PRODUCTS
CREATE TABLE IF NOT EXISTS `PRODUCTS` (
    `product_id`    BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name`          VARCHAR(255)    NOT NULL,
    `price`         DECIMAL(10, 2)  NOT NULL,
    `vat_rate`      DECIMAL(4, 2)   NOT NULL,
    `created`       TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP
);

---


-- Table: TRANSACTIONS
CREATE TABLE IF NOT EXISTS `TRANSACTIONS` (
    `transaction_id`    BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `device_id`         BIGINT UNSIGNED NOT NULL,
    `subtotal`          DECIMAL(10, 2)  NOT NULL,
    `total`             DECIMAL(11, 2)  NOT NULL,
    `currency`          CHAR(3)         NOT NULL,
    `auth_code`         VARCHAR(20),
    `created_at`        TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `status`            ENUM('active', 'suspended', 'closed') NOT NULL DEFAULT 'active',
    `payment_type`      ENUM('cash', 'credit', 'coupon') NOT NULL,
    FOREIGN KEY (`device_id`) REFERENCES `DEVICES`(`device_id`)
        ON DELETE RESTRICT
);

---

-- Table: CASH_PAYMENT_METHOD
CREATE TABLE IF NOT EXISTS `CASH_PAYMENT_METHOD` (
    `transaction_id` BIGINT UNSIGNED NOT NULL PRIMARY KEY,
    `received_amount`   DECIMAL(10, 2)  NOT NULL,
    `change_given`      DECIMAL(10, 2)  NOT NULL,
  FOREIGN KEY (`transaction_id`) REFERENCES `TRANSACTIONS`(`transaction_id`)
  ON DELETE CASCADE
);

---

-- Table: COUPON_PAYMENT_METHOD
CREATE TABLE IF NOT EXISTS `COUPON_PAYMENT_METHOD` (
    `transaction_id` BIGINT UNSIGNED NOT NULL PRIMARY KEY,
    `coupon_code`       VARCHAR(50)     NOT NULL,
    `coupon_value`      DECIMAL(10, 2)  NOT NULL,
    `expiry_date`       DATE            NOT NULL,
  FOREIGN KEY (`transaction_id`) REFERENCES `TRANSACTIONS`(`transaction_id`)
  ON DELETE CASCADE
);

---

-- Table: CREDIT_PAYMENT_METHOD
CREATE TABLE IF NOT EXISTS `CREDIT_PAYMENT_METHOD` (
    `transaction_id` BIGINT UNSIGNED NOT NULL PRIMARY KEY,
    `card_scheme`       VARCHAR(50)     NOT NULL,
    `card_last4`        CHAR(4)         NOT NULL,
    `auth_code`         VARCHAR(20)     NOT NULL,
    FOREIGN KEY (`transaction_id`) REFERENCES `TRANSACTIONS`(`transaction_id`)
        ON DELETE CASCADE
);

---

-- Table: TRANSACTION_ITEMS
CREATE TABLE IF NOT EXISTS `TRANSACTION_ITEMS` (
    `transaction_item_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `transaction_id`      BIGINT UNSIGNED NOT NULL,
    `product_id`          BIGINT UNSIGNED NOT NULL,
    `product_name`        VARCHAR(255)    NOT NULL,
    `sku`                 VARCHAR(20)     NOT NULL,
    `unit_price`          DECIMAL(10, 2)  NOT NULL,
    `quantity`            DECIMAL(10, 2)  NOT NULL,
    `vat_rate`            DECIMAL(10, 2)  NOT NULL,
    `total`               DECIMAL(10, 2)  NOT NULL,
    FOREIGN KEY (`transaction_id`) REFERENCES `TRANSACTIONS`(`transaction_id`)
        ON DELETE CASCADE,
    FOREIGN KEY (`product_id`) REFERENCES `PRODUCTS`(`product_id`)
        ON DELETE RESTRICT
);

