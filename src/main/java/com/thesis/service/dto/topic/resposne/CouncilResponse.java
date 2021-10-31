package com.thesis.service.dto.topic.resposne;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import com.thesis.service.model.topic.CouncilTable;
import com.thesis.service.utils.ContextAccessor;
import lombok.Data;

@Data
public class CouncilResponse {

  private long id;

  private String semesterName;

  private String subjectDepartmentName;

  private String location;

  private LocalDate reserveDate;

  private LocalTime startTime;

  private LocalTime endTime;

  private String note;

  private List<CouncilMemberResponse> members;

  private List<TopicResponse> topics;

  public CouncilResponse(CouncilTable entity) {
    var mapper = ContextAccessor.getModelConverter();
    mapper.map(entity, this);
    this.topics = mapper.map(entity.getTopics(), TopicResponse::new);
  }

}
