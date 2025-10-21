package com.backend.security;

import java.util.UUID;

import com.auth.grpc.AuthRequestGrpc;
import com.auth.grpc.AuthResponseGrpc;

import com.backend.security.exception.ApplicationAuthenticationException;
import com.backend.security.user.AuthUser;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  private final AuthUserCache authUserCache;

  private final UserService userService;

  private final PasswordEncoder passwordEncoder;

  public AuthService(
      AuthUserCache authUserCache, UserService userService, PasswordEncoder passwordEncoder) {
    this.authUserCache = authUserCache;
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

  public TokenDto login(AuthRequestGrpc authRequest) {

    UserResponseWithCredentials userCredentials = userService.getUserCredentialsByUsername(authRequest.getEmail());

    if (!passwordEncoder.matches(authRequest.getPassword(), userCredentials.passwordHash())) {
      throw new ApplicationAuthenticationException("Password is incorrect");
    }

    String token = UUID.randomUUID().toString();
    UserResponse userResponse = userCredentials.userResponse();
    AuthUser authUser = new AuthUser(userResponse.id(), userResponse.roles());

    authUserCache.login(token, authUser);

    return new TokenDto(token);
  }

  public void logout(String token) {

    authUserCache.logout(token);
  }
}
