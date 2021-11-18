package com.thesis.service.controller.topic;

import com.thesis.service.controller.ABaseController;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.service.topic.TopicService;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topics")
public class TopicController
    extends ABaseController<TopicTable, TopicService> {

  @GetMapping("/need-council")
  public Object findNeedAssignCouncil(
      @RequestParam long subjectDepartmentId,
      @RequestParam(defaultValue = "ASC") String direction,
      @RequestParam(defaultValue = "id") String sort) {
    Sort sortable = Sort.by(Direction.valueOf(direction), sort);
    return super.service.findNeedAssignCouncil(subjectDepartmentId, sortable);
  }

}
