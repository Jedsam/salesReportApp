package com.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.backend.security.AuthenticationInterceptor;
import com.backend.service.JwtService;

import io.grpc.ServerInterceptor;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

  public SecurityConfig() {
  }

  @Bean
  public ServerInterceptor authInterceptor(JwtService jwtService) {
    return new AuthenticationInterceptor(jwtService);
  }

  // I dont think this uses salting
  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }
}
