package com.backend.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "MERCHANTS")
public class Merchant {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "merchant_id", nullable = false, updatable = false)
  private Long merchantId;

  @Column(name = "name", nullable = false, length = 255)
  private String name;

  @Column(name = "business_name", nullable = false, length = 255)
  private String businessName;

  @Column(name = "email", nullable = false, length = 255, unique = true)
  private String email;

  @Column(name = "password_hash", nullable = false, length = 64)
  private String passwordHash;

  @Column(name = "phone", length = 30)
  private String phone;

  @Column(name = "address", length = 255)
  private String address;

  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false, length = 10)
  private Status status = Status.active;

  @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Shop> shops;

  public enum Status {
    active,
    suspended,
    closed
  }

  public Merchant() {
    this.createdAt = Instant.now();
  }

  // getters and setters ...
}
