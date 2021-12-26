package com.thesis.service.dto.system;

import lombok.Data;

@Data
public class SemesterPropertyResponse {

  private Long id;
  private Boolean defaultMid = true;

  private String createTopicStart = "";
  private String createTopicEnd = "";

  private String registerTopicStart = "";
  private String registerTopicEnd = "";

  private String executeTopicStart = "";
  private String executeTopicEnd = "";

  private String midMarkStart = "";
  private String midMarkEnd = "";

}
