package com.thesis.service.service.person;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import com.google.firebase.auth.FirebaseToken;
import com.thesis.service.config.firebase.FirebaseAuthenticationToken;
import com.thesis.service.constant.PersonRole;
import com.thesis.service.model.person.PersonTable;
import com.thesis.service.repository.person.PersonRepository;
import com.thesis.service.service.AbstractBaseService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonService extends AbstractBaseService<PersonTable, PersonRepository> {

  public FirebaseAuthenticationToken getAuthentication(FirebaseToken firebaseToken) {

    var person = super.repository.findByEmail(firebaseToken.getEmail());

    if (Objects.isNull(person)) {
      var newPerson = PersonTable.builder()
          .email(firebaseToken.getEmail())
          .roles(List.of(PersonRole.STUDENT.value, PersonRole.TEACHER.value))
          .createdAt(new Date())
          .updatedAt(new Date())
          .build();
      person = super.repository.save(newPerson);
    }

    var roles = person.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
        .collect(Collectors.toList());

    // return new FirebaseAuthenticationToken(person, null, roles);
    return null;
  }
}
