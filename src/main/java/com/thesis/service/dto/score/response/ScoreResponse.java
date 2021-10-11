package com.thesis.service.dto.score.response;

import com.thesis.service.dto.system.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ScoreResponse extends BaseResponse {

  private long topicId;

  private long settingTemplateId;

  private long teacherId;

  private long studentId;

  private long criterionId;

  private String score;

  private String comment;

}
