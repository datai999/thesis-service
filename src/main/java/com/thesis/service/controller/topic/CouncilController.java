package com.thesis.service.controller.topic;

import com.thesis.service.controller.ABaseController;
import com.thesis.service.model.topic.CouncilTable;
import com.thesis.service.service.topic.CouncilService;
import org.springframework.web.bind.annotation.RequestMapping;

// @RestController
@RequestMapping("/council")
public class CouncilController extends ABaseController<CouncilTable, CouncilService> {

}
