package com.thesis.service.config.firebase;

import java.util.Collection;
import com.thesis.service.model.user.UserTable;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import lombok.Getter;

@Getter
public class FirebaseAuthenticationToken extends AbstractAuthenticationToken {

  private final UserTable principal;
  private Object credentials;

  /**
   * This constructor can be safely used by any code that wishes to create a
   * <code>UsernamePasswordAuthenticationToken</code>, as the
   * {@link #isAuthenticated()} will return <code>false</code>.
   *
   */
  public FirebaseAuthenticationToken(UserTable principal, Object credentials) {
    super(null);
    this.principal = principal;
    this.credentials = credentials;
    setAuthenticated(false);
  }

  /**
   * This constructor should only be used by
   * <code>AuthenticationManager</code> or <code>AuthenticationProvider</code>
   * implementations that are satisfied with producing a trusted (i.e.
   * {@link #isAuthenticated()} = <code>true</code>) authentication token.
   *
   * @param principal
   * @param credentials
   * @param authorities
   */
  public FirebaseAuthenticationToken(UserTable principal, Object credentials,
      Collection<? extends GrantedAuthority> authorities) {
    super(authorities);
    this.principal = principal;
    this.credentials = credentials;
    super.setAuthenticated(true); // must use super, as we override
  }

  public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    if (isAuthenticated) {
      throw new IllegalArgumentException(
          "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
    }

    super.setAuthenticated(false);
  }

  @Override
  public void eraseCredentials() {
    super.eraseCredentials();
    credentials = null;
  }
}
