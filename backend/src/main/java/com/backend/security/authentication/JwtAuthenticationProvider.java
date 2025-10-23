package com.backend.security.authentication;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.stereotype.Component;

import com.backend.common.exceptions.TokenAuthenticationException;
import com.backend.security.user.AuthUser;
import com.backend.service.JwtService;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

  private final JwtService jwtService;

  public JwtAuthenticationProvider(JwtService jwtService) {
    this.jwtService = jwtService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    // The token extracted by the GrpcAuthenticationReader
    BearerTokenAuthenticationToken bearerToken = (BearerTokenAuthenticationToken) authentication;
    String token = bearerToken.getToken();

    try {
      // Use your JwtService to validate the token and extract user details (AuthUser)
      AuthUser authUser = jwtService.resolveJwtToken(token);

      // Convert AuthUser roles to Spring Security Granted Authorities
      List<SimpleGrantedAuthority> authorities = authUser.roles().stream()
          .map(role -> new SimpleGrantedAuthority(role.name()))
          .collect(Collectors.toList());

      // Return a fully authenticated token.
      // principal: the user object (AuthUser)
      // credentials: the JWT token (or null/empty string)
      // authorities: the granted roles
      return new UsernamePasswordAuthenticationToken(
          authUser,
          token,
          authorities);
    } catch (TokenAuthenticationException e) {
      // Re-throw a Spring Security exception for invalid token
      throw new BadCredentialsException("Invalid JWT token", e);
    }
  }

  @Override
  public boolean supports(Class<?> authentication) {
    // This provider only supports the token type extracted by the reader
    return BearerTokenAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
