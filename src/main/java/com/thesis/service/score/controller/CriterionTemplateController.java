package com.thesis.service.score.controller;

import com.thesis.service.common.controller.ABaseController;
import com.thesis.service.score.model.ScCriterionTemplateTable;
import com.thesis.service.score.service.CriterionTemplateService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/criterion/template")
public class CriterionTemplateController extends ABaseController<ScCriterionTemplateTable, CriterionTemplateService> {

}
