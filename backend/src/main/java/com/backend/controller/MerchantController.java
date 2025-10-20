package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.Merchant;
import com.backend.service.MerchantService;

@RestController
@RequestMapping(path = "/emp")
public class MerchantController {

  @Autowired
  MerchantService merchantService;

  // get all merchants
  @GetMapping("/merchants")
  public List<Merchant> getAllMerchants() {
    return merchantService.getAllMerchants();
  }

  // get an merchant by id
  @GetMapping("/merchant/{id}")
  public Merchant getMerchant(@PathVariable("id") int id) {
    return merchantService.getMerchantById(id);
  }

  // delete an merchant by id
  @DeleteMapping("/merchant/{id}")
  public void deleteMerchant(@PathVariable("id") int id) {
    merchantService.deleteMerchantById(id);
  }

  // create an merchant
  @PostMapping("/merchant")
  public void addMerchant(@RequestBody Merchant merchant) {
    merchantService.saveOrUpdate(merchant);
  }

  // update an merchant
  @PutMapping("/merchant")
  public void updateMerchant(@RequestBody Merchant merchant) {
    merchantService.saveOrUpdate(merchant);
  }
}
