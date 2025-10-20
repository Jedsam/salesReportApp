
package com.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
