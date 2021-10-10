package com.thesis.service.controller.score;

import com.thesis.service.constant.TopicRole;
import com.thesis.service.controller.ABaseController;
import com.thesis.service.model.score.TemplateTable;
import com.thesis.service.service.score.TemplateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/templates")
public class TemplateController
    extends ABaseController<TemplateTable, TemplateService> {

  @GetMapping("/template")
  public Object findTemplateGroupByRole() {
    return super.service.findTemplateGroupByRole();
  }

  @GetMapping("/topic")
  public Object findTemplateByRole(
      @RequestParam long topicId,
      @RequestParam String role) {
    return super.service.findTemplateByRole(topicId,
        "guide".equalsIgnoreCase(role) ? TopicRole.GUIDE_TEACHER : TopicRole.REVIEW_TEACHER);
  }

}
