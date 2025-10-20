package com.backend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "COUPON_PAYMENT_METHOD")
public class CouponPaymentMethod extends PaymentMethod {

  @Column(name = "coupon_code", nullable = false, length = 50)
  private String couponCode;

  @Column(name = "coupon_value", nullable = false, precision = 10, scale = 2)
  private BigDecimal couponValue;

  @Column(name = "expiry_date", nullable = false)
  private LocalDate expiryDate;

  // getters and setters ...
}
