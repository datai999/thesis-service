package com.thesis.service.dto.topic.resposne;

import com.thesis.service.dto.system.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CouncilRoleResponse extends BaseResponse {

  private int min;

  private int max;

  private int displayOrder;

}
