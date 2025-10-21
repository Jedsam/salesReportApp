package com.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "SHOPS")
public class Shop {

  @Id
  @Column(name = "shop_id", nullable = false, updatable = false, columnDefinition = "BINARY(16)")
  private UUID shopId;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "merchant_id", nullable = false)
  private Merchant merchant;

  @Column(name = "name", nullable = false, length = 255)
  private String name;

  @Column(name = "address", nullable = false, length = 255)
  private String address;

  @Column(name = "phone", length = 30)
  private String phone;

  @Column(name = "email", length = 255)
  private String email;

  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false, length = 10)
  private Status status = Status.active;

  @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Device> devices;

  public enum Status {
    active,
    suspended,
    closed
  }

  public Shop() {
    this.createdAt = Instant.now();
  }

  // getters and setters ...
}
