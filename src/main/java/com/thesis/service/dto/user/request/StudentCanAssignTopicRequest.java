package com.thesis.service.dto.user.request;

import java.util.List;
import lombok.Data;

@Data
public class StudentCanAssignTopicRequest {

  private boolean thesis = false;
  private List<Long> educationMethodIds = List.of();
  private List<Long> majorIds = List.of();

}
