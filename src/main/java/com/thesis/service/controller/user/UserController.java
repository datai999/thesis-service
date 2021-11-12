package com.thesis.service.controller.user;

import java.util.List;
import com.thesis.service.constant.UserPermission;
import com.thesis.service.constant.UserType;
import com.thesis.service.controller.ABaseController;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.service.user.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  @GetMapping("/type-{type}")
  public Object findByType(
      @PathVariable String type,
      @RequestParam(defaultValue = "ASC") String direction,
      @RequestParam(defaultValue = "id") String sort) {
    var entity = new UserTable().setType(UserType.valueOf(type.toUpperCase()));
    Sort sortable = Sort.by(Direction.valueOf(direction), sort);
    return service.findByExample(entity, sortable);
  }

  @GetMapping("/permissions")
  public Object getPermissions() {
    var requestUserIsAdmin = super.service.getAuth().getPermissions()
        .contains(UserPermission.ADMIN);
    return requestUserIsAdmin
        ? UserPermission.values()
        : List.of(UserPermission.values()).parallelStream()
            .filter(e -> !UserPermission.ADMIN.equals(e));
  }

}
