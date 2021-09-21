package com.thesis.service.dto.user.response;

import java.util.List;
import lombok.Data;

@Data
public class UserNotificationResponse {

  private List<NotificationResponse> all;
  private List<NotificationResponse> unseen;

}
