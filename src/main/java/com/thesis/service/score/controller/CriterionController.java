package com.thesis.service.score.controller;

import com.thesis.service.common.controller.ABaseController;
import com.thesis.service.score.model.ScCriterionTable;
import com.thesis.service.score.service.CriterionService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/criterion")
public class CriterionController extends ABaseController<ScCriterionTable, CriterionService> {

}
