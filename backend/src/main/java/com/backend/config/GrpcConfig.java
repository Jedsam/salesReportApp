package com.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import io.grpc.protobuf.services.ProtoReflectionService;

@Configuration
@EnableMethodSecurity
public class GrpcConfig {
  @Bean
  public ProtoReflectionService protoReflectionService() {
    // Registers the service that allows gRPC clients to inspect the server's
    // schema.
    return (ProtoReflectionService) ProtoReflectionService.newInstance();
  }
}
