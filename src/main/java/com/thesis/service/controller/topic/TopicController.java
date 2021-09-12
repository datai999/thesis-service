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

  @GetMapping
  public Object find(
      @RequestParam(defaultValue = "ASC") String direction,
      @RequestParam(defaultValue = "id") String sort) {
    Sort sortable = Sort.by(Direction.valueOf(direction), sort);
    return service.findAll(sortable);
  }

  @GetMapping("/{id}")
  public Object get(@PathVariable Long id) {
    var response = service.getRepository().findById(id);
    return response;
  }

  @PostMapping("/{topicId}/students")
  public Object studentRegisterTopic(@PathVariable Long topicId) {
    return super.service.studentRegister(topicId);
  }

}
