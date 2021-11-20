package com.thesis.service.dto.score.response;

import java.util.ArrayList;
import java.util.List;
import com.thesis.service.constant.MessageCode;
import com.thesis.service.dto.system.BaseResponse;
import com.thesis.service.model.score.TemplateTable;
import com.thesis.service.model.system.MajorTable;
import com.thesis.service.model.topic.CouncilRoleTable;
import com.thesis.service.utils.ContextAccessor;
import org.apache.commons.lang3.ObjectUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TemplateResponse extends BaseResponse {

  private Boolean thesis;
  private String type;

  private Boolean midSemester;
  private String markType;

  private Boolean guideTeacher;
  private Boolean reviewTeacher;
  private List<BaseResponse> councilRoles;
  private List<String> teacherRoles;

  private List<BaseResponse> majors;
  private List<String> majorNames;

  private Boolean numberMark;

  private CriterionResponse rootCriterion;

  public TemplateResponse(TemplateTable entity) {
    var mapper = ContextAccessor.getModelConverter();
    var messageSource = ContextAccessor.getMessageSource();

    mapper.map(entity, this);

    this.type = messageSource.getMessage(
        ObjectUtils.defaultIfNull(entity.getThesis(), true)
            ? MessageCode.THESIS
            : MessageCode.OUTLINE);

    this.markType = messageSource.getMessage(
        ObjectUtils.defaultIfNull(entity.getMidSemester(), false)
            ? MessageCode.Semester.MID
            : MessageCode.Semester.FINAL);

    this.majorNames = mapper.map(entity.getMajors(), MajorTable::getName);

    var teacherRoles = new ArrayList<String>();
    if (ObjectUtils.defaultIfNull(entity.getGuideTeacher(), false))
      teacherRoles.add(messageSource.getMessage(MessageCode.GUIDE_TEACHER));
    if (ObjectUtils.defaultIfNull(entity.getReviewTeacher(), false))
      teacherRoles.add(messageSource.getMessage(MessageCode.REVIEW_TEACHER));
    teacherRoles.addAll(mapper.map(entity.getCouncilRoles(), CouncilRoleTable::getName));
    this.teacherRoles = teacherRoles;
  }

}
