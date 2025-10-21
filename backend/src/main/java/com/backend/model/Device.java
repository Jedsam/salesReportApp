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
@Table(name = "DEVICES")
public class Device {

  @Id
  @Column(name = "device_id", nullable = false, updatable = false, columnDefinition = "BINARY(16)")
  private UUID deviceId;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "shop_id", nullable = false)
  private Shop shop;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "firmware_id", nullable = false)
  private Firmware firmware;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "model_id", nullable = false)
  private Model model;

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
