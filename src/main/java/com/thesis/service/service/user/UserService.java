package com.thesis.service.service.user;

import java.util.List;
import java.util.Objects;
import com.google.firebase.auth.FirebaseToken;
import com.thesis.service.advice.BusinessException;
import com.thesis.service.config.firebase.FirebaseAuthenticationToken;
import com.thesis.service.constant.MessageCode;
import com.thesis.service.constant.UserPermission;
import com.thesis.service.dto.user.CustomUserDetail;
import com.thesis.service.dto.user.response.UserResponse;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.user.UserRepository;
import com.thesis.service.service.ABaseService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ABaseService<UserTable, UserRepository>
    implements UserDetailsService {

  public FirebaseAuthenticationToken getAuthentication(String email) {

    var user = super.repository.findByEmail(email).orElseThrow();

    if (Objects.isNull(user)) {
      var newUser = new UserTable()
          .setEmail(email)
          .setPermission(UserPermission.STUDENT);
      user = super.repository.save(newUser);
    }

    var roles = Objects.nonNull(user.getPermission())
        ? List.of(new SimpleGrantedAuthority("ROLE_" + user.getPermission()))
        : null;

    return new FirebaseAuthenticationToken(user, null, roles);
  }

  public FirebaseAuthenticationToken getAuthentication(FirebaseToken firebaseToken) {
    var user = super.repository.findByEmail(firebaseToken.getEmail()).orElseThrow();

    if (Objects.isNull(user)) {
      var nameBulkhead = firebaseToken.getName().lastIndexOf(" ");
      var newUser = new UserTable()
          .setEmail(firebaseToken.getEmail())
          .setCode(String.valueOf(System.currentTimeMillis()).substring(6))
          .setFirstName(firebaseToken.getName().substring(0, nameBulkhead))
          .setLastName(firebaseToken.getName().substring(nameBulkhead + 1))
          .setPermission(UserPermission.STUDENT);
      user = super.repository.save(newUser);
    }

    var roles = Objects.nonNull(user.getPermission())
        ? List.of(new SimpleGrantedAuthority("ROLE_" + user.getPermission()))
        : null;

    return new FirebaseAuthenticationToken(user, null, roles);
  }

  @Override
  protected Class<?> getResponseClass() {
    return UserResponse.class;
  }

  public List<UserTable> shareFindByExample(UserTable entity, Sort sort) {
    entity.setCreatedAt(null).setUpdatedAt(null);
    var example = Example.of(entity);
    var response = this.repository.findAll(example, sort);
    if (UserPermission.TEACHER.equals(entity.getPermission())) {
      var headExample = Example.of(entity.setPermission(UserPermission.HEAD_SUBJECT_DEPARTMENT));
      var headResponse = this.repository.findAll(headExample, sort);
      response.addAll(headResponse);
    }
    return response;
  }

  @Override
  public Object findByExample(UserTable entity, Sort sort) {
    var response = this.shareFindByExample(entity, sort);
    return this.map(response);
  }

  public Object getRequestUser() {
    return mapper.map(super.getAuth(), UserResponse.class);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var user = super.repository.findByEmail(username)
        .orElseThrow(BusinessException.codeSupplier(MessageCode.User.EMAIL_NOT_FOUND));
    return new CustomUserDetail(user);
  }

}
