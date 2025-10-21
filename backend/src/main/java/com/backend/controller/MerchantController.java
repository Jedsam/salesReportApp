package com.backend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.Merchant;
import com.backend.security.Authentication;
import com.backend.service.MerchantService;

@RestController
@RequestMapping(path = "/merc")
public class MerchantController {

  @Autowired
  MerchantService merchantService;

  // get the current merchant information
  // @GetMapping("/merchant")
  // public Merchant getMerchant(Authentication authentication) {
  // // Authentication authentication =
  // // SecurityContextHolder.getContext().getAuthentication();
  // return
  // merchantService.getMerchantById(authentication.getPrincipal().getId());
  // }

  // delete an merchant by id
  @DeleteMapping("/merchant/{id}")
  public void deleteMerchant(@PathVariable UUID id) {
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
