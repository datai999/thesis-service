package com.thesis.service.dto.score;

import java.util.List;
import com.thesis.service.dto.system.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CriterionResponse extends BaseResponse {

  private long templateId;

  private boolean mark;

  private int displayOrder;

  // private Double minScore;

  // private Double maxScore;

  // private String formula;

  private Long parentId;

  private List<CriterionResponse> children;

}
