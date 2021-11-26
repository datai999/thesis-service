package com.thesis.service.dto.topic.resposne;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import com.thesis.service.model.BaseTable;
import com.thesis.service.model.topic.CouncilTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.utils.ContextAccessor;
import org.apache.commons.lang3.ObjectUtils;
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
    var topics = ObjectUtils.defaultIfNull(entity.getTopics(), new ArrayList<TopicTable>());
    this.totalTopic = topics.size();
    this.totalStudent = topics.stream()
        .map(topic -> ObjectUtils.defaultIfNull(topic.getStudents(), List.of()).size())
        .reduce(0, Integer::sum);
  }

}
