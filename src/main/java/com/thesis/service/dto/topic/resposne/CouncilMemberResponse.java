package com.thesis.service.dto.topic.resposne;

import com.thesis.service.dto.user.response.UserResponse;
import lombok.Data;

@Data
public class CouncilMemberResponse {

  private long id;

  private CouncilRoleResponse role;

  private UserResponse member;

}
