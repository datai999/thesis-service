package com.thesis.service.controller.score;

import com.thesis.service.controller.ABaseController;
import com.thesis.service.model.score.TemplateTable;
import com.thesis.service.service.score.TemplateService;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/templates")
public class TemplateController
    extends ABaseController<TemplateTable, TemplateService> {

  @PostMapping("/council")
  public Object getByCouncilMember(
      @RequestParam(defaultValue = "ASC") String direction,
      @RequestParam(defaultValue = "id") String sort,
      @RequestParam long councilId,
      @RequestParam long teacherId,
      @RequestBody TemplateTable entity) {
    Sort sortable = Sort.by(Direction.valueOf(direction), sort);
    return service.getByCouncilMember(sortable, entity, councilId, teacherId);
  }

}
