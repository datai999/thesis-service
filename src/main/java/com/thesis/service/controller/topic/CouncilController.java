package com.thesis.service.controller.topic;

import java.util.Set;
import com.thesis.service.controller.ABaseController;
import com.thesis.service.model.topic.CouncilTable;
import com.thesis.service.service.topic.CouncilService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/councils")
public class CouncilController extends ABaseController<CouncilTable, CouncilService> {

  @PatchMapping("/{councilId}/assign-topics")
  public Object assignTopic(
      @PathVariable long councilId,
      @RequestParam Set<Long> topicIds) {
    return super.service.assignTopic(councilId, topicIds);
  }

}
