package com.thesis.service.dto.system;

import com.thesis.service.constant.SemesterStatus;
import lombok.Data;

@Data
public class SemesterResponse {

  private Long id;
  private String name;
  private SemesterStatus status;
  private String registerTopicStart = "";
  private String registerTopicEnd = "";

  private SemesterPropertyResponse outline;
  private SemesterPropertyResponse thesis;

}
