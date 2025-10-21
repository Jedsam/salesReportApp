
package com.backend.model;

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
  @Column(name = "transaction_id", nullable = false, updatable = false, columnDefinition = "BINARY(16)")
  private UUID transactionId;

  @OneToOne
  @MapsId
  @JoinColumn(name = "transaction_id")
  private Transaction transaction;
}
