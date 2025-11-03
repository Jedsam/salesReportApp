package com.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@Entity
@Table(name = "PRODUCTS")
public class Product {

  @Id
  @GeneratedValue
  @Column(name = "product_id", nullable = false, updatable = false, columnDefinition = "BINARY(16)")
  @JdbcTypeCode(SqlTypes.BINARY)
  private UUID productId;

  @Column(name = "name", nullable = false, length = 255)
  private String name;

  @Column(name = "price", nullable = false, precision = 10, scale = 2)
  private BigDecimal price;

  @Column(name = "vat_rate", nullable = false, precision = 4, scale = 2)
  private BigDecimal vatRate;

  @Column(name = "deleted", nullable = false)
  private boolean deleted;

  @Column(name = "created", nullable = false, updatable = false)
  private Instant created;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<TransactionItem> transactionItems;

  public Product() {
    this.created = Instant.now();
  }

  // getters and setters ...
}
