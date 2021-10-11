package com.thesis.service.dto.score;

import com.thesis.service.dto.system.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CriterionRoleResponse extends BaseResponse {

  private BaseResponse template;


}
