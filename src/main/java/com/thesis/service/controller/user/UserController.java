package com.thesis.service.controller.user;

import java.util.List;
import javax.validation.Valid;
import com.thesis.service.constant.UserPermission;
import com.thesis.service.controller.ABaseController;
import com.thesis.service.dto.user.request.LoginRequest;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.service.user.JwtTokenService;
import com.thesis.service.service.user.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController extends ABaseController<UserTable, UserService> {

  private final AuthenticationManager authenticationManager;
  private final JwtTokenService jwtTokenService;

  @PostMapping("/login")
  public Object login(@Valid @RequestBody LoginRequest loginRequest) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            loginRequest.getUsername(),
            loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    return jwtTokenService.generateToken(super.service.getAuth());
  }

  @GetMapping("/permissions")
  public Object getPermissions() {
    return UserPermission.ADMIN.equals(super.service.getAuth().getPermission())
        ? UserPermission.values()
        : List.of(UserPermission.values()).parallelStream()
            .filter(e -> !UserPermission.ADMIN.equals(e));
  }

}
