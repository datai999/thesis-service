package com.thesis.service.topic.controller;

import com.thesis.service.common.controller.AbstractBaseController;
import com.thesis.service.topic.model.TpTopicTable;
import com.thesis.service.topic.repository.TpTopicRepository;
import com.thesis.service.topic.service.TopicService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topic")
public class TopicController
    extends AbstractBaseController<TpTopicTable, TpTopicRepository, TopicService> {


}
