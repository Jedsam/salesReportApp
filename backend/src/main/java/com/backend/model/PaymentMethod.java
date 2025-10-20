
package com.backend.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PaymentMethod {
  @Id
  private Long transactionId;

  @OneToOne
  @MapsId
  @JoinColumn(name = "transaction_id")
  private Transaction transaction;
}
