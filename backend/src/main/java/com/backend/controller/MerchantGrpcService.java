package com.backend.controller;

import java.util.UUID;

import org.springframework.grpc.server.service.GrpcService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.backend.model.Merchant;
import com.backend.proto.merchant.MerchantGetRequest;
import com.backend.proto.merchant.MerchantGetResponse;
import com.backend.proto.merchant.MerchantServiceGrpc.MerchantServiceImplBase;
import com.backend.security.user.AuthUser;
import com.backend.service.MerchantService;

import io.grpc.stub.StreamObserver;

@GrpcService
public class MerchantGrpcService extends MerchantServiceImplBase {

  private final MerchantService merchantService;

  public MerchantGrpcService(
      MerchantService merchantService) {
    this.merchantService = merchantService;
  }

  @Override
  @PreAuthorize("hasRole('MERCHANT')")
  public void getMerchantInfo(MerchantGetRequest loginAuthRequest,
      StreamObserver<MerchantGetResponse> responseObserver) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    AuthUser authUser = (AuthUser) authentication.getPrincipal();
    UUID merchantId = authUser.userId();

    Merchant merchant = merchantService.getMerchantById(merchantId);

    responseObserver.onNext(
        MerchantGetResponse.newBuilder()
            .setName(merchant.getName())
            .setBusinessName(merchant.getBusinessName())
            .setAddress(merchant.getAddress())
            .build());
    responseObserver.onCompleted();
  }
}
