package com.thesis.service.dto.user.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {

  @NotNull
  @NotBlank
  private String username;

  @NotNull
  @NotBlank
  private String password;

}
