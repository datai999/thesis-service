package com.thesis.service.controller.topic;

import java.util.stream.Collectors;
import com.thesis.service.controller.AbstractBaseController;
import com.thesis.service.dto.topic.resposne.TopicFlatResponse;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.service.topic.TopicService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topics")
public class TopicController
    extends AbstractBaseController<TopicTable, TopicService> {

  @GetMapping("/{id}")
  public Object get(@PathVariable Long id) {
    var response = service.getRepository().findById(id);
    return response;
  }

  @GetMapping("/{type}-flat")
  public Object reduce(@PathVariable String type) {
    var example = Example.of(new TopicTable().setThesis("thesis".equals(type)));
    Sort sortable = Sort.by(Direction.DESC, "id");
    return service.getRepository().findAll(example, sortable).stream()
        .map(TopicFlatResponse::from).collect(Collectors.toList());
  }

  @PostMapping("/{topicId}/student-register")
  public Object studentRegisterTopic(@PathVariable Long topicId) {
    return super.service.studentRegister(topicId);
  }

}
