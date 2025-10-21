package com.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import com.backend.security.SecurityAuthenticationFilter;

@Configuration
public class SecurityConfig {

  private final SecurityAuthenticationFilter securityAuthenticationFilter;

  private final AuthenticationEntryPoint authenticationEntryPoint;

  private final AccessDeniedHandler accessDeniedHandler;

  public SecurityConfig(
      SecurityAuthenticationFilter securityAuthenticationFilter,
      AuthenticationEntryPoint authenticationEntryPoint,
      AccessDeniedHandler accessDeniedHandler) {

    this.securityAuthenticationFilter = securityAuthenticationFilter;
    this.authenticationEntryPoint = authenticationEntryPoint;
    this.accessDeniedHandler = accessDeniedHandler;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.addFilterBefore(securityAuthenticationFilter, AuthorizationFilter.class)
        .authorizeHttpRequests(
            mather -> mather
                .requestMatchers(
                    "/swagger-ui.html",
                    "/swagger-ui/*",
                    "/v3/api-docs",
                    "/v3/api-docs/swagger-config")
                .permitAll())
        .authorizeHttpRequests(
            matcher -> matcher
                // method security will be evaluated after DSL configs,
                // so we have to define public paths upfront
                .requestMatchers(HttpMethod.POST, "/api/auth/login", "/api/users")
                .permitAll())
        .authorizeHttpRequests(matcher -> matcher.anyRequest().authenticated())
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(
            configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .exceptionHandling(
            customizer -> customizer
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint));

    return http.build();
  }

  // register NoOp AuthenticationManager to avoid log printed by default
  // autoconfiguration
  @Bean
  public AuthenticationManager noOpAuthenticationManager() {
    return authentication -> null;
  }

  // The prefix string before the role name
  // @Bean
  // static GrantedAuthorityDefaults grantedAuthorityDefaults() {
  // return new GrantedAuthorityDefaults("");
  // }

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }
}
