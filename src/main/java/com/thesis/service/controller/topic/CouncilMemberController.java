package com.thesis.service.controller.topic;

import com.thesis.service.controller.ABaseController;
import com.thesis.service.model.topic.CouncilMemberTable;
import com.thesis.service.service.topic.CouncilMemberService;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/council-members")
public class CouncilMemberController
    extends ABaseController<CouncilMemberTable, CouncilMemberService> {

  @PostMapping("/council")
  public Object getCouncil(
      @RequestParam(defaultValue = "ASC") String direction,
      @RequestParam(defaultValue = "id") String sort,
      @RequestBody CouncilMemberTable entity) {
    Sort sortable = Sort.by(Direction.valueOf(direction), sort);
    return service.getCouncil(entity, sortable);
  }

}
