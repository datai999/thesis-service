package com.thesis.service.dto.system;

import com.thesis.service.constant.SemesterStatus;
import lombok.Data;

@Data
public class SemesterResponse {

  private Long id;
  private String name;
  private String registerTopicStart = "";
  private String registerTopicEnd = "";
  private SemesterStatus status;

}
