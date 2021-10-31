package com.thesis.service.dto.score.response;

import java.util.List;
import com.thesis.service.constant.MessageCode;
import com.thesis.service.constant.TopicRole;
import com.thesis.service.dto.system.BaseResponse;
import com.thesis.service.model.score.SettingTemplateTable;
import com.thesis.service.model.topic.CouncilRoleTable;
import com.thesis.service.utils.ContextAccessor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
public class SettingTemplateGroupRoleResponse {

  private List<Role> outline = List.of();
  private List<Role> thesis = List.of();

  @Data
  @EqualsAndHashCode(callSuper = false)
  public static class SettingTemplateResponse extends BaseResponse {
    private long templateId;

    public SettingTemplateResponse(SettingTemplateTable entity) {
      ContextAccessor.getModelConverter().map(entity.getTemplate(), this);
      super.setId(entity.getId());
      super.setDeleted(entity.isDeleted());
      this.templateId = entity.getTemplate().getId();
    }
  }

  @Data
  public static class Role {
    private String name;
    private boolean thesis;
    private TopicRole topicRole;
    private BaseResponse councilRole;
    private List<SettingTemplateResponse> templates = List.of();

    public Role(String name, boolean thesis, TopicRole topicRole,
        List<SettingTemplateTable> settingTemplates) {
      this.name = name;
      this.thesis = thesis;
      this.topicRole = topicRole;
      this.templates = ContextAccessor.getModelConverter()
          .map(settingTemplates, SettingTemplateResponse::new);
    }

    public Role(CouncilRoleTable councilRole) {
      this.name = String.format("%s %s", councilRole.getName(),
          ContextAccessor.getMessageSource().getMessage(MessageCode.Council.COUNCIL));
      this.thesis = true;
      this.councilRole = ContextAccessor.getModelConverter().map(councilRole, BaseResponse.class);
      this.templates = ContextAccessor.getModelConverter()
          .map(councilRole.getSettingTemplates(), SettingTemplateResponse::new);
    }
  }

}
