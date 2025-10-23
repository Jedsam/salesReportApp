package com.backend.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.backend.service.JwtService;

import io.grpc.ServerInterceptor;
import net.devh.boot.grpc.server.security.authentication.BearerAuthenticationReader;
import net.devh.boot.grpc.server.security.authentication.GrpcAuthenticationReader;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

  public SecurityConfig() {
  }

  @Bean
  public GrpcAuthenticationReader grpcAuthenticationReader() {
    final List<GrpcAuthenticationReader> readers = new ArrayList<>();
    // The actual token class is dependent on your spring-security library
    // (OAuth2/JWT/...)
    readers.add(new BearerAuthenticationReader(accessToken -> new BearerTokenAuthenticationToken(accessToken)));
    return new CompositeGrpcAuthenticationReader(readers);
  }

  @Bean
  AuthenticationManager authenticationManager() {
    final List<AuthenticationProvider> providers = new ArrayList<>();
    providers.add(...); // Possibly JwtAuthenticationProvider
    return new ProviderManager(providers);
}

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }
}
