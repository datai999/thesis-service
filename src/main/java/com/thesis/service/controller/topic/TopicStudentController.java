package com.thesis.service.controller.topic;

import com.thesis.service.controller.ABaseController;
import com.thesis.service.model.topic.TopicStudentTable;
import com.thesis.service.service.topic.TopicStudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topic-student")
public class TopicStudentController
    extends ABaseController<TopicStudentTable, TopicStudentService> {

}
