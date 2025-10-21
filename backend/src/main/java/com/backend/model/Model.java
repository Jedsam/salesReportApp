package com.backend.model;

import java.util.UUID;

import com.backend.utils.UUIDBinaryConverter;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "MODEL")
public class Model {

  @Id
  @Column(name = "model_id", nullable = false, updatable = false, columnDefinition = "BINARY(16)")
  @Convert(converter = UUIDBinaryConverter.class)
  private UUID modelId;

  @Column(name = "model_name", nullable = false, unique = true, length = 50)
  private String modelName;

  // Constructors, getters, setters ...
}
