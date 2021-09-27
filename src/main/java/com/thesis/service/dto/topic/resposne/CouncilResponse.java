package com.thesis.service.dto.topic.resposne;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
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

}
