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
@Table(name = "MODEL")
public class Model {

  @Id
  @GeneratedValue
  @Column(name = "model_id", nullable = false, updatable = false, columnDefinition = "BINARY(16)")
  @JdbcTypeCode(SqlTypes.BINARY)
  private UUID modelId;

  @Column(name = "model_name", nullable = false, unique = true, length = 50)
  private String modelName;

  // Constructors, getters, setters ...
}
