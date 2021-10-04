package com.thesis.service.dto.score;

import com.thesis.service.dto.system.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CriterionTemplateResponse extends BaseResponse {

  private String description;

  private int displayOrder;

}
