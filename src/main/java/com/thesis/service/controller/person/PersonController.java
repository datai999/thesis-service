package com.thesis.service.controller.person;

import com.thesis.service.controller.AbstractBaseController;
import com.thesis.service.model.person.PersonTable;
import com.thesis.service.repository.person.PersonRepository;
import com.thesis.service.service.person.PersonService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController
    extends AbstractBaseController<PersonTable, PersonRepository, PersonService> {

  @PostMapping("/login")
  Object login() {
    return super.service.getAuth();
  }

}
