package com.thesis.service.dto.topic.request;

import java.util.List;
import com.thesis.service.model.topic.TpTopicTable;
import lombok.Data;

@Data
public class TopicCreateRequest {

  private TpTopicTable topic;
  private List<Long> teacherIds;

}
