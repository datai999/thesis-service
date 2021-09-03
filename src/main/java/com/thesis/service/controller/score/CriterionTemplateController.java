package com.thesis.service.controller.score;

import com.thesis.service.controller.ABaseController;
import com.thesis.service.model.model.ScCriterionTemplateTable;
import com.thesis.service.service.score.CriterionTemplateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/criterion/template")
public class CriterionTemplateController
    extends ABaseController<ScCriterionTemplateTable, CriterionTemplateService> {

}
