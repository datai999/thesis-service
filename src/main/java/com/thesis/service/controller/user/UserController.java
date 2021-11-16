package com.thesis.service.controller.user;

import java.util.List;
import com.thesis.service.constant.UserPermission;
import com.thesis.service.controller.ABaseController;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.service.user.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends ABaseController<UserTable, UserService> {

  @PostMapping("/login")
  public Object login() {
    return super.service.getRequestUser();
  }

  @GetMapping("/token")
  public Object getUser() {
    return super.service.getRequestUser();
  }

  @GetMapping("/permissions")
  public Object getPermissions() {
    return UserPermission.ADMIN.equals(super.service.getAuth().getPermission())
        ? UserPermission.values()
        : List.of(UserPermission.values()).parallelStream()
            .filter(e -> !UserPermission.ADMIN.equals(e));
  }

}
