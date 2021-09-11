package com.thesis.service.controller.user;

import java.util.List;
import com.thesis.service.constant.UserType;
import com.thesis.service.controller.AbstractBaseController;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.service.user.UserService;
import org.apache.commons.lang3.ObjectUtils;
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
public class UserController extends AbstractBaseController<UserTable, UserService> {

  @PostMapping("/login")
  Object login() {
    return super.service.getAuth();
  }

  @GetMapping("/token")
  Object getUser() {
    return super.service.getAuth();
  }

  @GetMapping("/{type}")
  Object findByType(
      @PathVariable String type,
      @RequestParam(defaultValue = "ASC") String direction,
      @RequestParam(defaultValue = "id") String sort) {
    String userType = type.subSequence(0, type.length() - 1).toString().toUpperCase();
    Sort sortable = Sort.by(Direction.valueOf(direction), sort);
    var response = super.service.findByType(UserType.valueOf(userType), sortable);
    return ObjectUtils.defaultIfNull(response, List.of());
  }

}
