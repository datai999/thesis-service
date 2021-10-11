package com.thesis.service.dto.score.response;

import com.thesis.service.dto.system.BaseResponse;
import com.thesis.service.dto.user.response.UserResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ScoreResponse extends BaseResponse {

  private BaseResponse topic;

  private BaseResponse settingTemplate;

  private UserResponse teacher;

  private UserResponse student;

  private BaseResponse criterion;

  private String score;

  private String comment;

}
