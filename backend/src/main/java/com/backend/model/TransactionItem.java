package com.backend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TRANSACTION_ITEMS")
public class TransactionItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "transaction_item_id", nullable = false, updatable = false)
  private Long transactionItemId;

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
