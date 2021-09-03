package com.thesis.service.topic.controller;

import java.util.stream.Collectors;
import com.thesis.service.common.controller.AbstractBaseController;
import com.thesis.service.topic.dto.response.TopicFlatResponse;
import com.thesis.service.topic.model.TpTopicTable;
import com.thesis.service.topic.repository.TpTopicRepository;
import com.thesis.service.topic.service.TopicService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topics")
public class TopicController
    extends AbstractBaseController<TpTopicTable, TpTopicRepository, TopicService> {

  @GetMapping("/{type}-flat")
  public Object reduce(@PathVariable String type) {
    var example = Example.of(new TpTopicTable().setThesis("thesis".equals(type)));
    Sort sortable = Sort.by(Direction.DESC, "id");
    return service.getRepository().findAll(example, sortable).stream()
        .map(TopicFlatResponse::from).collect(Collectors.toList());
  }

}
