package com.thesis.service.dto.score.response;

import com.thesis.service.dto.system.BaseResponse;
import com.thesis.service.dto.topic.resposne.CouncilRoleResponse;
import com.thesis.service.dto.user.response.UserResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ScoreResponse extends BaseResponse {

  private BaseResponse topic;

  private UserResponse teacher;

  private UserResponse student;

  private BaseResponse template;

  private BaseResponse criterion;

  private Boolean guideTeacher;

  private Boolean reviewTeacher;

  private CouncilRoleResponse councilRole;

  private String score;

  private String comment;

}
