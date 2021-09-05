package com.thesis.service.controller.person;

import javax.validation.Valid;
import com.thesis.service.dto.person.request.LoginRequest;
import com.thesis.service.service.person.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/login")
  Object login(@Valid @RequestBody LoginRequest loginRequest) {
    return userService.login(loginRequest);
  }

}
