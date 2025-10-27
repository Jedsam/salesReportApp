package com.backend.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.model.Merchant;

@Repository
public interface MerchantRepository extends CrudRepository<Merchant, UUID> {
  Optional<Merchant> findByUser_UserId(UUID userId);
}
