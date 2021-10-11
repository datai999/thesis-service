package com.thesis.service.dto.score.response;

import java.util.List;
import java.util.stream.Collectors;
import com.thesis.service.constant.MessageCode;
import com.thesis.service.constant.TopicRole;
import com.thesis.service.dto.system.BaseResponse;
import com.thesis.service.model.score.TemplateTable;
import com.thesis.service.model.topic.CouncilRoleTable;
import com.thesis.service.utils.ContextAccessor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SettingTemplateGroupRoleResponse {

  private List<Role> outline = List.of();
  private List<Role> thesis = List.of();

  @Data
  public static class Role {
    private String name;
    private boolean thesis;
    private TopicRole topicRole;
    private BaseResponse councilRole;
    private List<BaseResponse> templates = List.of();

    public Role(String name, boolean thesis, TopicRole topicRole,
        List<TemplateTable> templates) {
      this.name = name;
      this.thesis = thesis;
      this.topicRole = topicRole;
      this.templates = ContextAccessor.getModelConverter()
          .map(templates, BaseResponse.class);
    }

    public Role(CouncilRoleTable councilRole) {
      this.name = String.format("%s %s", councilRole.getName(),
          ContextAccessor.getMessageSource().getMessage(MessageCode.Council.COUNCIL));
      this.thesis = true;
      this.councilRole = ContextAccessor.getModelConverter().map(councilRole, BaseResponse.class);
      this.templates = councilRole.getSettingTemplates().parallelStream()
          .map(criterionRole -> ContextAccessor.getModelConverter()
              .map(criterionRole.getTemplate(), BaseResponse.class))
          .collect(Collectors.toList());
    }
  }

}
