package com.thesis.service.topic.controller;

import java.util.stream.Collectors;
import com.thesis.service.common.controller.AbstractBaseController;
import com.thesis.service.topic.dto.response.TopicFlatResponse;
import com.thesis.service.topic.model.TpTopicTable;
import com.thesis.service.topic.repository.TpTopicRepository;
import com.thesis.service.topic.service.TopicService;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topic")
public class TopicController
    extends AbstractBaseController<TpTopicTable, TpTopicRepository, TopicService> {

  @GetMapping("/flat")
  public Object reduce() {
    Sort sortable = Sort.by(Direction.DESC, "id");
    return service.getRepository().findAll(sortable).stream()
        .map(TopicFlatResponse::from).collect(Collectors.toList());
  }

}
