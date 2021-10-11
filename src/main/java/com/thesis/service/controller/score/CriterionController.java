package com.thesis.service.controller.score;

import com.thesis.service.controller.ABaseController;
import com.thesis.service.model.score.CriterionTable;
import com.thesis.service.service.score.CriterionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/criterions")
public class CriterionController extends ABaseController<CriterionTable, CriterionService> {

}
