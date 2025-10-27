package com.backend.controller;

import java.util.Collections;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.backend.common.enums.Role;
import com.backend.model.Merchant;
import com.backend.model.User;
import com.backend.proto.user.AuthServiceGrpc.AuthServiceImplBase;
import com.backend.proto.user.LoginAuthRequest;
import com.backend.proto.user.LoginAuthResponse;
import com.backend.proto.user.MerchantRegisterRequest;
import com.backend.proto.user.MerchantRegisterResponse;
import com.backend.security.user.AuthUser;
import com.backend.service.JwtService;
import com.backend.service.MerchantService;
import com.backend.service.UserService;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import io.grpc.Status;

@GrpcService
public class AuthGrpcService extends AuthServiceImplBase {

  private final UserService userService;

  private final MerchantService merchantService;

  private final PasswordEncoder passwordEncoder;

  private final JwtService jwtService;

  public AuthGrpcService(
      UserService userService,
      MerchantService merchantService,
      PasswordEncoder passwordEncoder,
      JwtService jwtService) {
    this.merchantService = merchantService;
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
  }

  @Override
  @PreAuthorize("isAnonymous()")
  public void login(LoginAuthRequest loginAuthRequest, StreamObserver<LoginAuthResponse> responseObserver) {
    User user = userService.getUserCredentialsByEmail(loginAuthRequest.getEmail());

    if (user == null || !passwordEncoder.matches(loginAuthRequest.getPassword(), user.getPasswordHash())) {
      responseObserver.onError(Status.UNAUTHENTICATED
          .withDescription("Invalid credentials")
          .asRuntimeException());
    }

    AuthUser authUser = new AuthUser(
        user.getUserId(),
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

  @Override
  @PreAuthorize("isAnonymous()")
  public void registerMerchant(MerchantRegisterRequest merchantRegisterRequest,
      StreamObserver<MerchantRegisterResponse> responseObserver) {
    // Check the validity of the information
    // Right now there is no check :)
    User user = new User();
    user.setEmail(merchantRegisterRequest.getEmail());
    user.setPasswordHash(passwordEncoder.encode(merchantRegisterRequest.getPassword()));
    user.setRole(Role.ROLE_MERCHANT);
    User registeredUser = userService.registerUser(user);

    Merchant merchant = new Merchant();
    merchant.setUser(registeredUser);
    merchant.setName(merchantRegisterRequest.getName());
    merchant.setBusinessName(merchantRegisterRequest.getBusinessName());
    merchant.setPhone(merchantRegisterRequest.getPhone());
    merchant.setAddress(merchantRegisterRequest.getAddress());
    merchantService.registerMerchant(merchant);

    AuthUser authUser = new AuthUser(
        user.getUserId(),
        Collections.singletonList(user.getRole()));

    String jwtToken = jwtService.createJwtToken(authUser);

    responseObserver
        .onNext(
            MerchantRegisterResponse.newBuilder()
                .setToken(jwtToken)
                .setMessage("Successfully registered Merchant {" + user.getUserId().toString() + "}")
                .build());

    responseObserver.onCompleted();
  }
}
