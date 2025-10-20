package com.backend.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "DEVICES")
public class Device {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "device_id", nullable = false, updatable = false)
  private Long deviceId;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "shop_id", nullable = false)
  private Shop shop;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "firmware_id", nullable = false)
  private Firmware firmware;

  @Column(name = "model", nullable = false, length = 100)
  private String model;

  @Column(name = "last_seen")
  private Instant lastSeen;

  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false, length = 10)
  private Status status = Status.active;

  @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Transaction> transactions;

  public enum Status {
    active,
    suspended,
    closed
  }

  public Device() {
    this.createdAt = Instant.now();
  }

  // getters and setters ...
}
