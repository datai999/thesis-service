package com.thesis.service.service.user;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import com.google.firebase.auth.FirebaseToken;
import com.thesis.service.config.firebase.FirebaseAuthenticationToken;
import com.thesis.service.constant.UserType;
import com.thesis.service.dto.user.response.UserResponse;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.user.UserRepository;
import com.thesis.service.service.AbstractBaseService;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractBaseService<UserTable, UserRepository> {

  public FirebaseAuthenticationToken getAuthentication(String email) {

    var user = super.repository.findByEmail(email);

    if (Objects.isNull(user)) {
      var newUser = UserTable.builder()
          .email(email)
          .type(UserType.STUDENT)
          .roles(List.of(UserType.STUDENT.name()))
          .createdAt(new Date())
          .updatedAt(new Date())
          .build();
      user = super.repository.save(newUser);
    }

    var roles = Objects.nonNull(user.getRoles())
        ? user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
            .collect(Collectors.toList())
        : null;

    return new FirebaseAuthenticationToken(user, null, roles);
  }

  public FirebaseAuthenticationToken getAuthentication(FirebaseToken firebaseToken) {
    return this.getAuthentication(firebaseToken.getEmail());
  }

  public Object getRequestUser() {
    return mapper.map(super.getAuth(), UserResponse.class);
  }

  public Object findByType(UserType type, Sort sort) {
    return super.mapper.map(super.repository.findByType(type, sort), UserResponse.class);
  }

}
