package com.thesis.service.service.person;

import java.util.Map;
import java.util.Set;
import com.thesis.service.dto.person.CustomUserDetails;
import com.thesis.service.dto.person.request.LoginRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  @Value("${jwt.secrect}")
  private String jwtSecret;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // TODO validate and get role
    var roles = Set.of(username);
    return new CustomUserDetails(roles);
  }

  public Object login(LoginRequest loginRequest) {
    // TODO hanlde loginRequest
    var jwt = Jwts.builder()
        .claim("username", loginRequest.getUsername())
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
    return Map.of("token", jwt);
  }

}
