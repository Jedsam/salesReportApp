package com.backend.model;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PaymentMethod {
  @Id
  @GeneratedValue
  @Column(name = "transaction_id", nullable = false, updatable = false, columnDefinition = "BINARY(16)")
  @JdbcTypeCode(SqlTypes.BINARY)
  private UUID transactionId;

  @OneToOne
  @MapsId
  @JoinColumn(name = "transaction_id")
  private Transaction transaction;
}
