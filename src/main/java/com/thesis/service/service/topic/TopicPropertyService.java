package com.thesis.service.service.topic;

import java.util.stream.Collectors;
import com.thesis.service.dto.topic.resposne.TopicResponse;
import com.thesis.service.model.topic.TopicPropertyTable;
import com.thesis.service.repository.topic.TopicPropertyRepository;
import com.thesis.service.service.ABaseService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TopicPropertyService
    extends ABaseService<TopicPropertyTable, TopicPropertyRepository> {

  @Override
  public Object findByExample(TopicPropertyTable entity, Sort sort) {
    entity.setCreatedAt(null).setUpdatedAt(null);
    var example = Example.of(entity);
    var response = this.repository.findAll(example, sort);
    return response.stream()
        .map(TopicPropertyTable::getTopic)
        .distinct()
        .map(TopicResponse::new)
        .collect(Collectors.toList());
  }

}
