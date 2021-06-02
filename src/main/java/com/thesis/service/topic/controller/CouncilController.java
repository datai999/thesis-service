package com.thesis.service.topic.controller;

import com.thesis.service.common.controller.ABaseController;
import com.thesis.service.topic.model.TpTopicTable;
import com.thesis.service.topic.service.TopicService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/council")
public class CouncilController extends ABaseController<TpTopicTable, TopicService> {

}
