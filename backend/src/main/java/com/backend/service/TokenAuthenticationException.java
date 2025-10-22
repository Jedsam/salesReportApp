package com.backend.service;

public class TokenAuthenticationException extends RuntimeException {

  TokenAuthenticationException(String error) {
    super(error);
  }

}
