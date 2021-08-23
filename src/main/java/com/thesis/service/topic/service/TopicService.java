package com.thesis.service.topic.service;

import com.thesis.service.common.repository.SyMajorRepository;
import com.thesis.service.common.service.ABaseService;
import com.thesis.service.topic.model.TpTopicTable;
import com.thesis.service.topic.repository.TpTopicRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicService extends ABaseService<TpTopicTable, TpTopicRepository> {

  private final SyMajorRepository majorRepository;

  @Override
  public void preBuild(TpTopicTable topic) {
    if (!CollectionUtils.isEmpty(topic.getMajorId()))
      topic.setMajor(majorRepository.findAllById(topic.getMajorId()));
  }

}
