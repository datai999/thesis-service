package com.thesis.service.service.score;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.thesis.service.constant.MessageCode;
import com.thesis.service.constant.TopicRole;
import com.thesis.service.dto.score.response.SettingTemplateGroupRoleResponse;
import com.thesis.service.dto.score.response.SettingTemplateResponse;
import com.thesis.service.model.score.SettingTemplateTable;
import com.thesis.service.repository.score.SettingTemplateRepository;
import com.thesis.service.repository.topic.CouncilRoleRepository;
import com.thesis.service.repository.topic.TopicRepository;
import com.thesis.service.service.ABaseService;
import com.thesis.service.service.MessageSourceService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SettingTemplateService
    extends ABaseService<SettingTemplateTable, SettingTemplateRepository> {

  private final CouncilRoleRepository councilRoleRepository;
  private final TopicRepository topicRepository;
  private final MessageSourceService messageSourceService;
  private final CriterionService criterionService;

  protected Class<?> getResponseClass() {
    return SettingTemplateResponse.class;
  }

  public Object findTemplateGroupByRole() {
    var guideTeacher = messageSourceService.getMessage(MessageCode.GUIDE_TEACHER);
    var reviewTeacher = messageSourceService.getMessage(MessageCode.REVIEW_TEACHER);

    List<SettingTemplateGroupRoleResponse.Role> outlineTemplates = List.of(
        new SettingTemplateGroupRoleResponse.Role(guideTeacher, false, TopicRole.GUIDE_TEACHER,
            super.repository.findTemplateByTopicRole(false, TopicRole.GUIDE_TEACHER)),
        new SettingTemplateGroupRoleResponse.Role(reviewTeacher, false, TopicRole.REVIEW_TEACHER,
            super.repository.findTemplateByTopicRole(false, TopicRole.REVIEW_TEACHER)));

    List<SettingTemplateGroupRoleResponse.Role> thesisTemplates = new ArrayList<>(Arrays.asList(
        new SettingTemplateGroupRoleResponse.Role(guideTeacher, true, TopicRole.GUIDE_TEACHER,
            super.repository.findTemplateByTopicRole(true, TopicRole.GUIDE_TEACHER)),
        new SettingTemplateGroupRoleResponse.Role(reviewTeacher, true, TopicRole.REVIEW_TEACHER,
            super.repository.findTemplateByTopicRole(true, TopicRole.REVIEW_TEACHER))));

    councilRoleRepository.findByDeletedFalseOrderByDisplayOrder().stream().forEach(
        councilRole -> thesisTemplates.add(new SettingTemplateGroupRoleResponse.Role(councilRole)));

    return new SettingTemplateGroupRoleResponse(outlineTemplates, thesisTemplates);
  }

  public Object findTemplateByRole(long topicId, TopicRole role) {
    var topic = topicRepository.findById(topicId).orElseThrow();
    var queryResult = super.repository.findTemplateByTopicRole(topic.getThesis(), role);
    queryResult.parallelStream().forEach(settingTemplate -> criterionService
        .sortChildren(settingTemplate.getTemplate().getRootCriterion()));
    return super.map(queryResult);
  }

}
