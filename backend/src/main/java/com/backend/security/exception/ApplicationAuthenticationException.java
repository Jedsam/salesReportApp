package com.backend.security.exception;

import lombok.Getter;

@Getter
public class ApplicationAuthenticationException extends RuntimeException {

  public ApplicationAuthenticationException(String msg) {
    super(msg);
  }
}
