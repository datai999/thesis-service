package com.thesis.service.controller.topic;

import com.thesis.service.controller.ABaseController;
import com.thesis.service.model.topic.TopicAssignTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.service.topic.TopicAssignService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topics/{topicId}")
public class TopicAssignController
    extends ABaseController<TopicAssignTable, TopicAssignService> {

  @PutMapping("/assign-reviews")
  public Object assignReview(@RequestBody TopicTable topic) {
    return super.service.assignReview(topic);
  }

}
