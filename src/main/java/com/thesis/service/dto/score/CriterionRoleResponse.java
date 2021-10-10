package com.thesis.service.dto.score;

import com.thesis.service.dto.system.BaseResponse;
import com.thesis.service.model.score.CriterionTable;
import com.thesis.service.model.score.TemplateTable;
import com.thesis.service.utils.ContextAccessor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CriterionRoleResponse extends BaseResponse {

  private BaseResponse template;

  public CriterionRoleResponse(TemplateTable entity, String name, CriterionTable template) {
    var mapper = ContextAccessor.getModelConverter();
    mapper.map(entity, this);
    mapper.map(template, this.template);
    super.setName(name);
  }

}
