package com.backend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "TRANSACTIONS")
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "transaction_id", nullable = false, updatable = false)
  private Long transactionId;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "device_id", nullable = false)
  private Device device;

  @Column(name = "subtotal", nullable = false, precision = 10, scale = 2)
  private BigDecimal subtotal;

  @Column(name = "total", nullable = false, precision = 10, scale = 2)
  private BigDecimal total;

  @Column(name = "currency", nullable = false, length = 3)
  private String currency;

  @Column(name = "auth_code", length = 20)
  private String authCode;

  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false, length = 10)
  private Status status = Status.active;

  @Enumerated(EnumType.STRING)
  @Column(name = "payment_type", nullable = false, length = 10)
  private PaymentType paymentType;

  @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<TransactionItem> items;

  public enum Status {
    active,
    suspended,
    closed
  }

  public enum PaymentType {
    cash,
    credit,
    coupon
  }

  public Transaction() {
    this.createdAt = Instant.now();
  }

  // getters and setters ...
}
