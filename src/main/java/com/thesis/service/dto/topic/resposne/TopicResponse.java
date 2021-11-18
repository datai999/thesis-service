package com.thesis.service.dto.topic.resposne;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import com.thesis.service.dto.MultiLangDto;
import com.thesis.service.dto.system.BaseResponse;
import com.thesis.service.dto.user.response.UserResponse;
import com.thesis.service.model.system.EducationMethodTable;
import com.thesis.service.model.system.MajorTable;
import com.thesis.service.model.topic.TopicAssignTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.utils.ContextAccessor;
import lombok.Data;
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

  private String subjectDepartmentName;

  private int minStudentTake = 1;
  private int maxStudentTake = 3;

  private String description;
  private String task;
  private String documentReference;

  private List<UserResponse> students;
  private List<UserResponse> guideTeachers;
  private List<UserResponse> reviewTeachers;

  private CouncilInTopicResponse council;

  @Data
  private static class CouncilInTopicResponse {
    private long id;

    private String semesterName;

    private String subjectDepartmentName;

    private String location;

    private LocalDate reserveDate;

    private LocalTime startTime;

    private LocalTime endTime;

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
    this.students = mapper.map(entity.getStudents(), UserResponse.class);
    this.guideTeachers = mapper.map(entity.getGuideTeachers(), UserResponse.class);
    this.reviewTeachers = mapper.map(entity.getReviewTeachers(), UserResponse.class);
    this.council = mapper.map(entity.getCouncil(), CouncilInTopicResponse.class);
  }

  public TopicResponse(TopicAssignTable topicAssign) {
    this(topicAssign.getTopic());
  }

}
