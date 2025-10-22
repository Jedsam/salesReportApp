package com.backend.security.authentication;

import java.io.Serializable;
import java.security.Principal;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

// Represents the token for an authentication request or for an authentication principal
public interface Authentication extends Principal, Serializable {

  // Returns a list of user roles (as a collection of GrantedAuthorities), which
  // will be checked by the authorization part of the Spring Security
  Collection<? extends GrantedAuthority> getAuthorities();

  // Designed to transfer credentials in the Authentication, when it’s used as a
  // “credentials carrying bag for yet unauthenticated request”.
  // This is usually a password
  Object getCredentials();

  // Used to store additional data about the request.
  // This might be an IP
  Object getDetails();

  // This method should return data about authenticated user. This exact method is
  // called under the hoods when you put “@AuthenticatedPrincipal” annotation on
  // the parameter of method in the controller where you want your authentication
  // user to arrive. Note, that this method returns Object, which means the class
  // containing the data about the authenticated user is not required to implement
  // any mandatory interfaces.
  Object getPrincipal();

  // the primary method of Authentication, which indicates if Authentication is
  // “authenticated”
  boolean isAuthenticated();

  // Allows to implement changing of the value of “authenticated” flag inside the
  // Authentication implementation. Used to switch the Authentication from
  // “credentials holder” (“unauthenticated”) to “request authentication details
  // holder” (“authenticated”). Usually, it’s better not to leverage this method
  // and just recreate the Authentication object, but with isAuthenticated() set
  // to “true”.
  void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException;

}
