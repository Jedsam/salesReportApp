package com.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "MODEL")
public class Model {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "model_id", nullable = false, updatable = false)
  private Long modelId;

  @Column(name = "model_name", nullable = false, unique = true, length = 50)
  private String modelName;

  // Constructors, getters, setters ...
}
