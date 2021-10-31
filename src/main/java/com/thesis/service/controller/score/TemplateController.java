package com.thesis.service.controller.score;

import com.thesis.service.controller.ABaseController;
import com.thesis.service.model.score.TemplateTable;
import com.thesis.service.service.score.TemplateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/templates")
public class TemplateController
    extends ABaseController<TemplateTable, TemplateService> {

}
