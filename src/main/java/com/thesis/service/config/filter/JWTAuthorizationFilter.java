package com.thesis.service.config.filter;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.thesis.service.service.user.JwtTokenService;
import com.thesis.service.service.user.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {

  private static final String HEADER = "X-auth";
  private static final String PREFIX = "Bearer ";

  private final JwtTokenService jwtTokenService;
  private final UserService userService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    if (!hasToken(request)) {
      SecurityContextHolder.clearContext();
      filterChain.doFilter(request, response);
      return;
    }

    UsernamePasswordAuthenticationToken oauth = this.authenticate(request);
    oauth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(oauth);

    filterChain.doFilter(request, response);
  }

  private boolean hasToken(HttpServletRequest request) {
    String authenticationHeader = request.getHeader(HEADER);
    return Objects.nonNull(authenticationHeader) && authenticationHeader.startsWith(PREFIX);
  }

  private UsernamePasswordAuthenticationToken authenticate(HttpServletRequest request) {
    String token = request.getHeader(HEADER).replace(PREFIX, "");
    var username = jwtTokenService.getUsername(token);
    var userDetails = userService.loadUserByUsername(username);
    return new UsernamePasswordAuthenticationToken(
        userDetails, null, userDetails.getAuthorities());
  }

}
