package com.thesis.service.config.filter;

import java.io.IOException;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.thesis.service.service.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {

  private final static String HEADER = "Authorization";
  private final static String PREFIX = "Bearer ";

  @Value("${jwt.secrect}")
  private String jwtSecret;

  private final UserService customUserDetailService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    if (!hasToken(request, response)) {
      SecurityContextHolder.clearContext();
      filterChain.doFilter(request, response);
      return;
    }

    UsernamePasswordAuthenticationToken oauth = this.authenticate(request);
    oauth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(oauth);

    filterChain.doFilter(request, response);
  }

  private boolean hasToken(HttpServletRequest request, HttpServletResponse response) {
    String authenticationHeader = request.getHeader(HEADER);
    return (Objects.nonNull(authenticationHeader) && authenticationHeader.startsWith(PREFIX));
  }

  private UsernamePasswordAuthenticationToken authenticate(HttpServletRequest request) {
    String token = request.getHeader(HEADER).replace(PREFIX, "");

    Claims claims = Jwts.parser()
        .setSigningKey(jwtSecret)
        .parseClaimsJws(token)
        .getBody();

    var username = String.valueOf(claims.get("username"));
    // var userDetails = customUserDetailService.loadUserByUsername(username);
    // return new UsernamePasswordAuthenticationToken(userDetails,
    // null, userDetails.getAuthorities());

    return null;
  }

}
