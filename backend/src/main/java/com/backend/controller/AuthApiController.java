package com.backend.controller.authentication;

import java.util.Optional;

import com.auth.grpc.TokenAuthResponse;
import com.backend.service.AuthService;
import com.auth.grpc.LoginAuthRequest;
import com.auth.grpc.LoginAuthResponse;
import com.auth.grpc.TokenAuthRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.grpc.stub.StreamObserver;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthApiController {

  private final AuthService authService;

  public AuthApiController(AuthService authService) {
    this.authService = authService;
  }

  @PreAuthorize("isAnonymous()")
  @PostMapping("/login")
  public LoginAuthResponse login(@RequestBody LoginAuthRequest authRequestGrpc) {

    StreamObserver<LoginAuthResponse> myResponse = new StreamObserver<LoginAuthResponse>();
    authService.loginWithEmail(authRequestGrpc, myResponse);
    return myResponse;
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping("/logout")
  @SecurityRequirement(name = OpenApiConstants.TOKEN_SECURITY_REQUIREMENT)
  public void logout(HttpServletRequest httpServletRequest) {

    String token = Optional.ofNullable(httpServletRequest.getHeader(AuthConstants.AUTHORIZATION_HEADER))
        .orElseThrow();

    authService.logout(token);
  }
}
