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
  private String topicStart = "";
  private String topicEnd = "";
  private String thesisStart = "";
  private String thesisEnd = "";

}
