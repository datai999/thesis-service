package com.thesis.service.service.topic;

import com.thesis.service.dto.topic.resposne.TopicStudentResponse;
import com.thesis.service.model.topic.TopicStudentTable;
import com.thesis.service.repository.topic.TopicStudentRepository;
import com.thesis.service.service.ABaseService;
import org.springframework.stereotype.Service;

@Service
public class TopicStudentService extends ABaseService<TopicStudentTable, TopicStudentRepository> {

  @Override
  protected Class<?> getResponseClass() {
    return TopicStudentResponse.class;
  }

}
