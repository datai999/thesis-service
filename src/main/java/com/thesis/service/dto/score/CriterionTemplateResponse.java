package com.thesis.service.dto.score;

import lombok.Data;

@Data
public class CriterionTemplateResponse {

  private long id;

  private String name;

  private String description;

  private int displayOrder;

}
