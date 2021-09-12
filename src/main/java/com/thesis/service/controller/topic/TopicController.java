package com.thesis.service.controller.topic;

import com.thesis.service.controller.AbstractBaseController;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.service.topic.TopicService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topics")
public class TopicController
    extends AbstractBaseController<TopicTable, TopicService> {

  @PostMapping("/{topicId}/students")
  public Object studentRegisterTopic(@PathVariable Long topicId) {
    return super.service.studentRegister(topicId);
  }

}
