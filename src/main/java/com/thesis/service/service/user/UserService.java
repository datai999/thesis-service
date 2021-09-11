package com.thesis.service.service.user;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import com.google.firebase.auth.FirebaseToken;
import com.thesis.service.config.firebase.FirebaseAuthenticationToken;
import com.thesis.service.constant.UserType;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.user.UserRepository;
import com.thesis.service.service.AbstractBaseService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractBaseService<UserTable, UserRepository> {

  public FirebaseAuthenticationToken getAuthentication(FirebaseToken firebaseToken) {

    var user = super.repository.findByEmail(firebaseToken.getEmail());

    if (Objects.isNull(user)) {
      var newUser = UserTable.builder()
          .email(firebaseToken.getEmail())
          .roles(List.of(UserType.STUDENT.name()))
          .createdAt(new Date())
          .updatedAt(new Date())
          .build();
      user = super.repository.save(newUser);
    }

    var roles = user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
        .collect(Collectors.toList());

    return new FirebaseAuthenticationToken(user, null, roles);
  }

}
