package com.thesis.service.controller.topic;

import com.thesis.service.controller.ABaseController;
import com.thesis.service.model.topic.TopicPropertyTable;
import com.thesis.service.service.topic.TopicPropertyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topics-property")
public class TopicPropertyController
    extends ABaseController<TopicPropertyTable, TopicPropertyService> {

}
