package com.thesis.service.dto.topic.resposne;

import java.util.List;
import com.thesis.service.dto.user.response.UserResponse;
import com.thesis.service.model.system.EducationMethodTable;
import com.thesis.service.model.system.MajorTable;
import com.thesis.service.model.topic.TopicAssignTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.utils.ContextAccessor;
import lombok.Data;

@Data
public class TopicResponse {

  private Long id;
  private Integer semester;
  private Integer minStudentTake = 1;
  private Integer maxStudentTake = 3;
  private String description;
  private String task;
  private String documentReference;

  private List<String> names;
  private String type;
  private List<String> majorNames;
  private List<String> educationMethodNames;
  private List<UserResponse> students;
  private List<UserResponse> guideTeachers;

  public TopicResponse(TopicTable entity) {

    var mapper = ContextAccessor.getModelConverter();

    mapper.map(entity, this);

    this.names = mapper.map(entity.getName());
    this.educationMethodNames =
        mapper.map(entity.getEducationMethods(), EducationMethodTable::getName);
    this.majorNames = mapper.map(entity.getMajors(), MajorTable::getName);
    this.students = mapper.map(entity.getStudents(), UserResponse.class);
    this.guideTeachers = mapper.map(entity.getGuideTeachers(), UserResponse.class);

  }

  public TopicResponse(TopicAssignTable topicAssign) {
    this(topicAssign.getTopic());
  }

}
