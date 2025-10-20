package com.backend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "CASH_PAYMENT_METHOD")
public class CashPaymentMethod extends PaymentMethod {

  @Column(name = "received_amount", nullable = false, precision = 10, scale = 2)
  private BigDecimal receivedAmount;

  @Column(name = "change_given", nullable = false, precision = 10, scale = 2)
  private BigDecimal changeGiven;

  // getters and setters ...
}
