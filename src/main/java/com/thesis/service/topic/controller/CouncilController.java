package com.thesis.service.topic.controller;

import com.thesis.service.common.controller.ABaseController;
import com.thesis.service.topic.model.TpCouncilTable;
import com.thesis.service.topic.service.CouncilService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/council")
public class CouncilController extends ABaseController<TpCouncilTable, CouncilService> {

}
