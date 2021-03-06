package com.thesis.service.dto.topic.resposne;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.thesis.service.constant.TopicState;
import com.thesis.service.dto.MultiLangDto;
import com.thesis.service.dto.system.BaseResponse;
import com.thesis.service.dto.user.response.UserResponse;
import com.thesis.service.model.system.EducationMethodTable;
import com.thesis.service.model.system.MajorTable;
import com.thesis.service.model.topic.TopicGuideTeacherTable;
import com.thesis.service.model.topic.TopicStudentTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.service.topic.TopicService;
import com.thesis.service.utils.ContextAccessor;
import org.apache.commons.lang3.ObjectUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TopicResponse {

  private long id;

  private MultiLangDto name;
  private List<String> names;

  private BaseResponse semester;

  private boolean thesis;
  private String type;

  private List<BaseResponse> educationMethods;
  private List<String> educationMethodNames;

  private List<BaseResponse> majors;
  private List<String> majorNames;

  private BaseResponse subjectDepartment;
  private String subjectDepartmentName;

  private int minStudentTake = 1;
  private int maxStudentTake = 3;

  private String description;
  private String task;
  private String documentReference;

  private List<StudentResponse> students;
  private List<GuideTeacherResponse> guideTeachers;
  private List<String> guideTeacherNames;
  private List<UserResponse> reviewTeachers;
  private List<String> reviewTeacherNames;

  private CouncilInTopicResponse council;

  private TopicState state;
  private String stateName;

  @Data
  @EqualsAndHashCode(callSuper = false)
  @NoArgsConstructor
  private static class StudentResponse extends UserResponse {
    private Long relationId;
    private Boolean midPass;
    private String reason;

    public StudentResponse(TopicStudentTable entity) {
      ContextAccessor.getModelConverter().map(entity.getStudent(), this);
      this.relationId = entity.getId();
      this.midPass = entity.getMidPass();
      this.reason = entity.getReason();
    }
  }

  @Data
  @EqualsAndHashCode(callSuper = false)
  @NoArgsConstructor
  private static class GuideTeacherResponse extends UserResponse {
    private Boolean main;

    public GuideTeacherResponse(TopicGuideTeacherTable entity) {
      ContextAccessor.getModelConverter().map(entity.getGuideTeacher(), this);
      this.main = entity.getMain();
    }
  }

  @Data
  private static class CouncilInTopicResponse {
    private long id;

    private String semesterName;

    private String subjectDepartmentName;

    private String location;

    private LocalDate reserveDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private String note;

    private List<CouncilMemberResponse> members;
  }

  public TopicResponse(TopicTable entity) {

    var mapper = ContextAccessor.getModelConverter();

    mapper.map(entity, this);

    this.names = mapper.map(entity.getName());
    this.educationMethods = mapper.map(entity.getEducationMethods(), BaseResponse.class);
    this.educationMethodNames =
        mapper.map(entity.getEducationMethods(), EducationMethodTable::getName);
    this.majors = mapper.map(entity.getMajors(), BaseResponse.class);
    this.majorNames = mapper.map(entity.getMajors(), MajorTable::getName);
    this.students = mapper.map(entity.getStudents(), StudentResponse::new);
    this.reviewTeachers = mapper.map(entity.getReviewTeachers(), UserResponse.class);
    this.reviewTeacherNames = mapper.map(this.reviewTeachers, UserResponse::getFullName);
    this.council = mapper.map(entity.getCouncil(), CouncilInTopicResponse.class);

    this.guideTeachers = new ArrayList<>();
    guideTeachers.addAll(entity.getGuideTeachers().stream()
        .filter(e -> ObjectUtils.defaultIfNull(e.getMain(), false))
        .map(GuideTeacherResponse::new).collect(Collectors.toList()));
    guideTeachers.addAll(entity.getGuideTeachers().stream()
        .filter(e -> !ObjectUtils.defaultIfNull(e.getMain(), false))
        .map(GuideTeacherResponse::new).collect(Collectors.toList()));
    this.guideTeacherNames = mapper.map(this.guideTeachers, GuideTeacherResponse::getFullName);

    this.state = ContextAccessor.getBean(TopicService.class).getState(entity);
    this.stateName = this.state.getStateName();
  }

}
