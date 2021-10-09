package com.thesis.service.service.score;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import com.thesis.service.constant.MessageCode;
import com.thesis.service.constant.TopicRole;
import com.thesis.service.dto.score.CriterionGroupRoleResponse;
import com.thesis.service.dto.score.CriterionRoleResponse;
import com.thesis.service.model.score.CriterionRoleTable;
import com.thesis.service.repository.score.CriterionRoleRepository;
import com.thesis.service.repository.topic.CouncilRoleRepository;
import com.thesis.service.service.ABaseService;
import com.thesis.service.service.MessageSourceService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CriterionRoleService
    extends ABaseService<CriterionRoleTable, CriterionRoleRepository> {

  private final CouncilRoleRepository councilRoleRepository;
  private final MessageSourceService messageSourceService;

  @Override
  protected Function<CriterionRoleTable, ?> mapping() {
    return entity -> {
      var name = (Objects.nonNull(entity.getCouncilRole()))
          ? entity.getCouncilRole().getName()
          : messageSourceService.getMessage(
              TopicRole.GUIDE_TEACHER.equals(entity.getTopicRole())
                  ? MessageCode.GUIDE_TEACHER
                  : MessageCode.REVIEW_TEACHER);
      return new CriterionRoleResponse(entity, name, entity.getTemplate());
    };
  }

  public Object findTemplateGroupByRole() {
    var guideTeacher = messageSourceService.getMessage(MessageCode.GUIDE_TEACHER);
    var reviewTeacher = messageSourceService.getMessage(MessageCode.REVIEW_TEACHER);

    List<CriterionGroupRoleResponse.Role> outlineTemplates = List.of(
        new CriterionGroupRoleResponse.Role(guideTeacher, false, TopicRole.GUIDE_TEACHER,
            super.repository.findTopicRoleCriterion(false, TopicRole.GUIDE_TEACHER)),
        new CriterionGroupRoleResponse.Role(reviewTeacher, false, TopicRole.REVIEW_TEACHER,
            super.repository.findTopicRoleCriterion(false, TopicRole.REVIEW_TEACHER)));

    List<CriterionGroupRoleResponse.Role> thesisTemplates = new ArrayList<>(Arrays.asList(
        new CriterionGroupRoleResponse.Role(guideTeacher, true, TopicRole.GUIDE_TEACHER,
            super.repository.findTopicRoleCriterion(true, TopicRole.GUIDE_TEACHER)),
        new CriterionGroupRoleResponse.Role(reviewTeacher, true, TopicRole.REVIEW_TEACHER,
            super.repository.findTopicRoleCriterion(true, TopicRole.REVIEW_TEACHER))));

    councilRoleRepository.findByDeletedFalseOrderByDisplayOrder().stream().forEach(
        councilRole -> thesisTemplates.add(new CriterionGroupRoleResponse.Role(councilRole)));

    return new CriterionGroupRoleResponse(outlineTemplates, thesisTemplates);
  }

}