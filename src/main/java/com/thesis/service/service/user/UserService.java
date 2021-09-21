package com.thesis.service.service.user;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import com.google.firebase.auth.FirebaseToken;
import com.thesis.service.config.firebase.FirebaseAuthenticationToken;
import com.thesis.service.constant.UserType;
import com.thesis.service.dto.topic.resposne.TopicResponse;
import com.thesis.service.dto.user.response.TopicOfUserResponse;
import com.thesis.service.dto.user.response.UserResponse;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.user.UserRepository;
import com.thesis.service.service.ABaseService;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ABaseService<UserTable, UserRepository> {

  public FirebaseAuthenticationToken getAuthentication(String email) {

    var user = super.repository.findByEmail(email);

    if (Objects.isNull(user)) {
      var newUser = new UserTable()
          .setEmail(email)
          .setType(UserType.STUDENT)
          .setRoles(List.of(UserType.STUDENT.name()));
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
    var user = super.repository.findByEmail(firebaseToken.getEmail());

    if (Objects.isNull(user)) {
      var nameBulkhead = firebaseToken.getName().lastIndexOf(" ");
      var newUser = new UserTable()
          .setEmail(firebaseToken.getEmail())
          .setCode(String.valueOf(System.currentTimeMillis()).substring(6))
          .setFirstName(firebaseToken.getName().substring(0, nameBulkhead))
          .setLastName(firebaseToken.getName().substring(nameBulkhead + 1))
          .setType(UserType.STUDENT)
          .setRoles(List.of(UserType.STUDENT.name()));
      user = super.repository.save(newUser);
    }

    var roles = Objects.nonNull(user.getRoles())
        ? user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
            .collect(Collectors.toList())
        : null;

    return new FirebaseAuthenticationToken(user, null, roles);
  }

  @Override
  protected Class<?> getResponseClass() {
    return UserResponse.class;
  }

  public Object getRequestUser() {
    return mapper.map(super.getAuth(), UserResponse.class);
  }

  public Object findByType(UserType type, Sort sort) {
    return super.mapper.map(super.repository.findByType(type, sort), UserResponse.class);
  }

  public Object findTopics() {
    return new TopicOfUserResponse()
        .setExecute(super.mapper.map(super.getAuth().getTopicExecutes(), TopicResponse::new))
        .setGuide(super.mapper.map(super.getAuth().getTopicGuides(), TopicResponse::new))
        .setReview(super.mapper.map(super.getAuth().getTopicReviews(), TopicResponse::new));
  }

}
