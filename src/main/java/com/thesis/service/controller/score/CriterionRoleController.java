package com.thesis.service.controller.score;

import com.thesis.service.controller.ABaseController;
import com.thesis.service.model.score.CriterionRoleTable;
import com.thesis.service.service.score.CriterionRoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/criterion-roles")
public class CriterionRoleController
    extends ABaseController<CriterionRoleTable, CriterionRoleService> {

  @GetMapping("/template")
  public Object findTemplateGroupByRole() {
    return super.service.findTemplateGroupByRole();
  }

}
