package com.thesis.service.dto.score;

import java.util.List;
import com.thesis.service.dto.system.BaseResponse;
import lombok.Data;

@Data
public class CriterionResponse extends BaseResponse {

  private String description;

  private int displayOrder;

  private Double minScore;

  private Double maxScore;

  private String formula;

  private Long parentId;

  private List<CriterionResponse> children;

}
