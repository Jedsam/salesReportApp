package com.backend.service;

import java.io.Serializable;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.grpc.LoginAuthRequest;
import com.auth.grpc.LoginAuthResponse;
import com.backend.security.UserResponseWithCredentials;
import com.backend.security.exception.ApplicationAuthenticationException;
import com.backend.security.user.AuthUser;

import io.grpc.stub.StreamObserver;

import com.auth.grpc.AuthServiceGrpc.AuthServiceImplBase;

@GrpcService
public class AuthService extends AuthServiceImplBase {

  private final UserService userService;

  private final PasswordEncoder passwordEncoder;

  private final JwtService jwtService;

  public AuthService(
      UserService userService, PasswordEncoder passwordEncoder, JwtService jwtService) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
  }

  @Override
  public void loginWithEmail(LoginAuthRequest loginAuthRequest, StreamObserver<LoginAuthResponse> responseObserver) {

    UserResponseWithCredentials userCredentials = userService.getUserCredentialsByUsername(loginAuthRequest.getEmail());

    if (!passwordEncoder.matches(loginAuthRequest.getPassword(), userCredentials.passwordHash())) {
      throw new ApplicationAuthenticationException("Password is incorrect");
    }

    UserResponse userResponse = userCredentials.userResponse();
    AuthUser authUser = new AuthUser(userResponse.id(), userResponse.roles());

    String jwtToken = jwtService.createJwtToken(authUser);

    ;

    responseObserver
        .onNext(
            LoginAuthResponse.newBuilder().setToken(jwtToken).setMessage("Successfully created the token!").build());
    responseObserver.onCompleted();
  }
}
