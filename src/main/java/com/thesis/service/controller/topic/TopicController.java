package com.thesis.service.controller.topic;

import com.thesis.service.controller.AbstractBaseController;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.service.topic.TopicService;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topics")
public class TopicController
    extends AbstractBaseController<TopicTable, TopicService> {

  @GetMapping("/{type}")
  public Object findByType(
      @PathVariable String type,
      @RequestParam(defaultValue = "ASC") String direction,
      @RequestParam(defaultValue = "id") String sort) {
    var entity = new TopicTable().setThesis("thesis".equals(type));
    Sort sortable = Sort.by(Direction.valueOf(direction), sort);
    return service.findByExample(entity, sortable);
  }

  @PostMapping("/{topicId}/students")
  public Object studentRegisterTopic(@PathVariable Long topicId) {
    return super.service.studentRegister(topicId);
  }

}
