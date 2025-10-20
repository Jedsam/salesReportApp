package com.backend.security.exception;

import lombok.Getter;

@Getter
public class TokenAuthenticationException extends RuntimeException {

  public TokenAuthenticationException(String message) {
    super(message);
  }
}
