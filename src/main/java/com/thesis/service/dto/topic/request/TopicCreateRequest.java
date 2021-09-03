package com.thesis.service.dto.topic.request;

import java.util.List;
import com.thesis.service.model.topic.TopicTable;
import lombok.Data;

@Data
public class TopicCreateRequest {

  private TopicTable topic;
  private List<Long> teacherIds;

}
