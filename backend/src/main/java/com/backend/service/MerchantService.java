
package com.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.model.Merchant;
import com.backend.repository.MerchantRepository;

@Service
public class MerchantService {

  @Autowired
  MerchantRepository repository;

  // get an merchant by id
  public Merchant getMerchantById(UUID id) {
    return repository.findById(id).get();
  }

  // get all merchants
  public List<Merchant> getAllMerchants() {
    List<Merchant> merchants = new ArrayList<Merchant>();
    repository.findAll().forEach(merchant -> merchants.add(merchant));
    return merchants;
  }

  // create or update an merchant
  public void saveOrUpdate(Merchant merchant) {
    repository.save(merchant);
  }

  // delete an merchant by id
  public void deleteMerchantById(UUID id) {
    repository.deleteById(id);
  }
}
