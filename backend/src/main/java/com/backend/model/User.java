package com.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import com.backend.common.enums.Role;
import com.backend.utils.UUIDBinaryConverter;

@Getter
@Setter
@Entity
@Table(name = "USERS")
public class User {
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(name = "user_id", nullable = false, updatable = false, columnDefinition = "BINARY(16)")
  @Convert(converter = UUIDBinaryConverter.class)
  private UUID userId;

  @Column(name = "email", nullable = false, length = 255, unique = true)
  private String email;

  @Column(name = "password_hash", nullable = false, length = 64)
  private String passwordHash;

  @Enumerated(EnumType.STRING)
  @Column(name = "role", nullable = false, length = 20)
  private Role role;
}
