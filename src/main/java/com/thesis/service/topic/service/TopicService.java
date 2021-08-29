package com.thesis.service.topic.service;

import com.thesis.service.common.service.ABaseService;
import com.thesis.service.topic.model.TpTopicTable;
import com.thesis.service.topic.repository.TpTopicRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicService extends ABaseService<TpTopicTable, TpTopicRepository> {
  @Override
  protected void preBuild(TpTopicTable entity) {
    // not pre build

  }

}
