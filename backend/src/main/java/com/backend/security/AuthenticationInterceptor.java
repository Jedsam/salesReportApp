package com.backend.security;

import com.backend.security.authentication.UserAuthentication;
import com.backend.service.JwtService;

import io.grpc.Context;
import io.grpc.Contexts;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.Status;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthenticationInterceptor implements ServerInterceptor {

  private final JwtService jwtService;

  public static final Context.Key<SecurityContext> SECURITY_CONTEXT_KEY = Context.key("security-context");

  private static final String ANONYMOUS_KEY = UUID.randomUUID().toString();

  public AuthenticationInterceptor(JwtService jwtService) {
    this.jwtService = jwtService;
  }

  private static final String LOGIN_METHOD = "com.backend.proto.user.AuthService/Login";
  private static final String REGISTER_MERCHANT_METHOD = "com.backend.proto.user.AuthService/RegisterMerchant";

  @Override
  public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
      ServerCall<ReqT, RespT> call,
      Metadata headers, // Contains the incoming request headers/metadata
      ServerCallHandler<ReqT, RespT> next // Handles the subsequent call
  ) {
    log.debug("AuthenticationInterceptor: Inside interceptor");

    // Check if the RPC method is the 'login' method ends with login which might be
    // a security leak( check for full name)
    String methodFullName = call.getMethodDescriptor().getFullMethodName();
    if (methodFullName.equals(LOGIN_METHOD) || methodFullName.equals(REGISTER_MERCHANT_METHOD)) {
      log.debug("AuthenticationInterceptor: method is login/register");

      SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

      AnonymousAuthenticationToken anonymousAuth = new AnonymousAuthenticationToken(
          ANONYMOUS_KEY, // The key for the token
          "anonymousUser", // The principal name
          AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS") // Standard anonymous role
      );

      securityContext.setAuthentication(anonymousAuth);

      SecurityContextHolder.setContext(securityContext);

      Context context = Context.current().withValue(SECURITY_CONTEXT_KEY, securityContext);
      return Contexts.interceptCall(context, call, headers, next);
    }

    log.debug("AuthenticationInterceptor: method requires authorization ");
    String token = headers.get(Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER));

    // Reject the call if the token is missing/invalid
    if (token == null || !token.startsWith("Bearer ")) {
      call.close(Status.UNAUTHENTICATED.withDescription("Missing authentication token"), headers);
      return new ServerCall.Listener<ReqT>() {
      }; // Return an empty listener
    }
    log.debug("AuthenticationInterceptor: Token is exists/valid");

    try {
      log.debug("AuthenticationInterceptor: Validating JWT token");
      UserAuthentication authentication = new UserAuthentication(jwtService.resolveJwtToken(token.substring(7)));
      log.debug("AuthenticationInterceptor: JWT token validated successfully");

      SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
      securityContext.setAuthentication(authentication);
      SecurityContextHolder.setContext(securityContext);

      Context context = Context.current().withValue(SECURITY_CONTEXT_KEY, securityContext);
      return Contexts.interceptCall(context, call, headers, next);
    } catch (Exception e) {
      // Handle specific exceptions (e.g., ExpiredJwtException)
      log.warn("Authentication failed: {}", e.getMessage());
      call.close(Status.UNAUTHENTICATED.withDescription("Invalid token: " + e.getMessage()), headers);
      return new ServerCall.Listener<ReqT>() {
      };
    }
  }
}
