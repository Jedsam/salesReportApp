package com.backend.controller;

import java.util.Collections;

import org.springframework.grpc.server.service.GrpcService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.auth.grpc.LoginAuthRequest;
import com.auth.grpc.LoginAuthResponse;
import com.backend.model.User;
import com.backend.security.user.AuthUser;
import com.backend.service.JwtService;
import com.backend.service.UserService;

import io.grpc.stub.StreamObserver;
import io.grpc.Status;

import com.auth.grpc.AuthServiceGrpc.AuthServiceImplBase;

@GrpcService
public class AuthGrpcService extends AuthServiceImplBase {

  private final UserService userService;

  private final PasswordEncoder passwordEncoder;

  private final JwtService jwtService;

  public AuthGrpcService(
      UserService userService, PasswordEncoder passwordEncoder, JwtService jwtService) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
  }

  @Override
  public void login(LoginAuthRequest loginAuthRequest, StreamObserver<LoginAuthResponse> responseObserver) {
    User user = userService.getUserCredentialsByEmail(loginAuthRequest.getEmail());

    if (user == null || !passwordEncoder.matches(loginAuthRequest.getPassword(), user.getPasswordHash())) {
      responseObserver.onError(Status.UNAUTHENTICATED
          .withDescription("Invalid credentials")
          .asRuntimeException());
    }

    AuthUser authUser = new AuthUser(
        user.getUserId().toString(),
        Collections.singletonList(user.getRole()));

    String jwtToken = jwtService.createJwtToken(authUser);

    responseObserver
        .onNext(
            LoginAuthResponse.newBuilder()
                .setToken(jwtToken)
                .setMessage("Successfully created the token!")
                .build());

    responseObserver.onCompleted();
  }
}
