package com.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.model.User;
import com.backend.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  UserRepository repository;

  public User getUserCredentialsByEmail(String email) {
    return repository.findByEmail(email).orElse(null);
  }

}
