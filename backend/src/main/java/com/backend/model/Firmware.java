package com.backend.model;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "FIRMWARE")
public class Firmware {

  @Id
  @GeneratedValue
  @Column(name = "firmware_id", nullable = false, updatable = false, columnDefinition = "BINARY(16)")
  @JdbcTypeCode(SqlTypes.BINARY)
  private UUID firmwareId;

  @Column(name = "firmware_version", nullable = false, unique = true, length = 50)
  private String firmwareVersion;

  // Constructors, getters, setters ...
}
