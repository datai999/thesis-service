package com.thesis.service.dto.score.response;

import com.thesis.service.constant.TopicRole;
import com.thesis.service.dto.system.BaseResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class SettingTemplateResponse extends BaseResponse {

  private BaseResponse major;

  private boolean thesis;

  private TopicRole topicRole;

  private BaseResponse councilRole;

  private TemplateResponse template;

}
