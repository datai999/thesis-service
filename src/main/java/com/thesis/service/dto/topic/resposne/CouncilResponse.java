package com.thesis.service.dto.topic.resposne;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import com.thesis.service.model.BaseTable;
import com.thesis.service.model.topic.CouncilTable;
import com.thesis.service.utils.ContextAccessor;
import lombok.Data;

@Data
public class CouncilResponse {

  private long id;

  private String semesterName;

  private BaseTable subjectDepartment;
  private String subjectDepartmentName;

  private String location;

  private LocalDate reserveDate;

  private LocalTime startTime;

  private LocalTime endTime;

  private String note;

  private List<CouncilMemberResponse> members;

  private Integer totalTopic;

  private Integer totalStudent;

  public CouncilResponse(CouncilTable entity) {
    ContextAccessor.getModelConverter().map(entity, this);
    this.totalTopic = entity.getTopics().size();
    this.totalStudent = entity.getTopics().stream()
        .map(topic -> topic.getStudents().size())
        .reduce(0, Integer::sum);
  }

}
