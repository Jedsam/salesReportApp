package com.backend.common.exceptions;

public class TokenAuthenticationException extends RuntimeException {

  public TokenAuthenticationException(String error) {
    super(error);
  }

}
