package com.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.model.Merchant;

@Repository
public interface MerchantRepository extends CrudRepository<Merchant, Integer> {
}
