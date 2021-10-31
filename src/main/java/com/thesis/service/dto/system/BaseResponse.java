package com.thesis.service.dto.system;

import lombok.Data;

@Data
public class BaseResponse {

  private long id;
  private String name;
  private String description;
  private boolean deleted;

}
