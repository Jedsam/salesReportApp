
package com.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PAYMENT_METHODS")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PaymentMethod {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "payment_method_id", nullable = false, updatable = false)
  private Long paymentMethodId;

  // getters and setters ...
}
