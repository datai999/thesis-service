package com.thesis.service.dto.score.response;

import java.util.List;
import com.thesis.service.dto.score.CriterionResponse;
import com.thesis.service.dto.system.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TemplateResponse extends BaseResponse {

  private BaseResponse major;

  private List<CriterionResponse> criterions;

}
