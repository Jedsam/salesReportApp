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
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthenticationInterceptor implements ServerInterceptor {

  private final JwtService jwtService;

  private static final Context.Key<SecurityContext> SECURITY_CONTEXT_KEY = Context.key("security-context");

  public AuthenticationInterceptor(JwtService jwtService) {
    this.jwtService = jwtService;
  }

  @Override
  public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
      ServerCall<ReqT, RespT> call,
      Metadata headers, // Contains the incoming request headers/metadata
      ServerCallHandler<ReqT, RespT> next // Handles the subsequent call
  ) {

    // Check if the RPC method is the 'login' method ends with login which might be
    // a security leak( check for full name)
    if (call.getMethodDescriptor().getFullMethodName().endsWith("Login")) {
      // Allow unauthenticated access to the Login RPC
      return next.startCall(call, headers);
    }

    String token = headers.get(Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER));

    // Reject the call if the token is missing/invalid
    if (token == null || !token.startsWith("Bearer ")) {
      call.close(Status.UNAUTHENTICATED.withDescription("Missing authentication token"), headers);
      return new ServerCall.Listener<ReqT>() {
      }; // Return an empty listener
    }

    try {
      UserAuthentication authentication = new UserAuthentication(jwtService.resolveJwtToken(token.substring(7)));

      SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
      securityContext.setAuthentication(authentication);

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
