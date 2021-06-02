package com.thesis.service.topic.service;

import com.thesis.service.common.service.ABaseService;
import com.thesis.service.topic.model.TpTopicTable;
import com.thesis.service.topic.repository.TpTopicRepository;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicService extends ABaseService<TpTopicTable, TpTopicRepository> {

  @Override
  public void preBuild(TpTopicTable topic) {
    if (!CollectionUtils.isEmpty(topic.getMajorId()))
      topic.setMajor(super.constRepository.findAllById(topic.getMajorId()));
  }

}
