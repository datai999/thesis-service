package com.thesis.service.controller.topic;

import com.thesis.service.constant.TopicRole;
import com.thesis.service.controller.ABaseController;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.service.topic.TopicService;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topics")
public class TopicController
    extends ABaseController<TopicTable, TopicService> {

  @GetMapping("/{type}")
  public Object findByType(
      @PathVariable String type,
      @RequestParam(defaultValue = "ASC") String direction,
      @RequestParam(defaultValue = "id") String sort) {
    var entity = new TopicTable().setThesis("thesis".equals(type));
    Sort sortable = Sort.by(Direction.valueOf(direction), sort);
    return service.findByExample(entity, sortable);
  }

  @GetMapping("/user")
  public Object findTopicsByRole(
      @RequestParam Long userId,
      @RequestParam String topicRole,
      @RequestParam(defaultValue = "ASC") String direction,
      @RequestParam(defaultValue = "id") String sort) {
    TopicRole role = TopicRole.valueOf(topicRole.toUpperCase());
    Sort sortable = Sort.by(Direction.valueOf(direction), sort);
    return super.service.findByUserAndRole(userId, role, sortable);
  }

  @PostMapping("/{topicId}/students")
  public Object studentRegisterTopic(@PathVariable Long topicId) {
    return super.service.studentRegister(topicId);
  }

  @DeleteMapping("/{topicId}/students/cancel")
  public Object studentCancel(@PathVariable Long topicId) {
    return super.service.studentCancel(topicId);
  }

}
