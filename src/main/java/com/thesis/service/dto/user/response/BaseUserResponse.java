package com.thesis.service.dto.user.response;

import lombok.Data;

@Data
public class BaseUserResponse {

  private Long id;

  private String code;

  private String fullName;

  private String email;

}
