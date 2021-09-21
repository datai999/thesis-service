package com.thesis.service.dto.user.response;

import lombok.Data;

@Data
public class NotificationResponse {

  private long id;
  private String createdAt;
  private String message;
  private boolean seen;

}
