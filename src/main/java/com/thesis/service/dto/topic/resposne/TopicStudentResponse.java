package com.thesis.service.dto.topic.resposne;

import com.thesis.service.dto.system.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class TopicStudentResponse extends BaseResponse {

  private BaseResponse topic;

  private Long studentId;

  private Boolean midPass;

  private String reason;

}
