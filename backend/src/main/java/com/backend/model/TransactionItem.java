package com.backend.model;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "TRANSACTION_ITEMS")
public class TransactionItem {

  @Id
  @GeneratedValue
  @Column(name = "transaction_item_id", nullable = false, updatable = false, columnDefinition = "BINARY(16)")
  @JdbcTypeCode(SqlTypes.BINARY)
  private UUID transactionItemId;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "transaction_id", nullable = false)
  private Transaction transaction;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @Column(name = "product_name", nullable = false, length = 255)
  private String productName;

  @Column(name = "sku", nullable = false, length = 20)
  private String sku;

  @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
  private BigDecimal unitPrice;

  @Column(name = "quantity", nullable = false, precision = 10, scale = 2)
  private BigDecimal quantity;

  @Column(name = "vat_rate", nullable = false, precision = 10, scale = 2)
  private BigDecimal vatRate;

  @Column(name = "total", nullable = false, precision = 10, scale = 2)
  private BigDecimal total;

  // getters and setters ...
}
