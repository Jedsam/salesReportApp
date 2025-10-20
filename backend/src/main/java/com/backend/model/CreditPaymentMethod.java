package com.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CREDIT_PAYMENT_METHOD")
public class CreditPaymentMethod extends PaymentMethod {

  @Column(name = "card_scheme", nullable = false, length = 50)
  private String cardScheme;

  @Column(name = "card_last4", nullable = false, length = 4)
  private String cardLast4;

  @Column(name = "auth_code", nullable = false, length = 20)
  private String authCode;

  // getters and setters ...
}
