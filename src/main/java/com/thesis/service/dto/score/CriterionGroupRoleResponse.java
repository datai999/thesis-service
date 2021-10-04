package com.thesis.service.dto.score;

import java.util.List;
import java.util.stream.Collectors;
import com.thesis.service.dto.system.BaseResponse;
import com.thesis.service.model.score.CriterionTable;
import com.thesis.service.model.topic.CouncilRoleTable;
import com.thesis.service.utils.ContextAccessor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CriterionGroupRoleResponse {

  private List<Role> outline = List.of();
  private List<Role> thesis = List.of();

  @Data
  public static class Role {
    private String name;
    private List<BaseResponse> templates = List.of();

    public Role(String name, List<CriterionTable> templates) {
      this.name = name;
      this.templates = ContextAccessor.getModelConverter().map(templates, BaseResponse.class);
    }

    public Role(CouncilRoleTable councilRole) {
      this.name = councilRole.getName();
      this.templates = councilRole.getTemplates().parallelStream()
          .map(criterionRole -> ContextAccessor.getModelConverter()
              .map(criterionRole.getTemplate(), BaseResponse.class))
          .collect(Collectors.toList());
    }
  }

}
