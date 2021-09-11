package com.thesis.service.controller.user;

import com.thesis.service.controller.AbstractBaseController;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.service.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends AbstractBaseController<UserTable, UserService> {

  @PostMapping("/login")
  Object login() {
    return super.service.getAuth();
  }

}
