package com.thesis.service.topic.dto.request;

import java.util.List;
import com.thesis.service.model.topic.TpTopicTable;
import lombok.Data;

@Data
public class TopicCreateRequest {

  private TpTopicTable topic;
  private List<Long> teacherIds;

}
