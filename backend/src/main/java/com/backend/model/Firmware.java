package com.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "FIRMWARE")
public class Firmware {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "firmware_id", nullable = false, updatable = false)
  private Long firmwareId;

  @Column(name = "firmware_version", nullable = false, unique = true, length = 50)
  private String firmwareVersion;

  // Constructors, getters, setters ...
}
