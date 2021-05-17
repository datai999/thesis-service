package com.thesis.service.topic.service;

import java.util.Objects;

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
  public TpTopicTable build(TpTopicTable topic) {
    if (Objects.isNull(topic))
      return null;
    if (!CollectionUtils.isEmpty(topic.getMajorId()))
      topic.setMajor(super.constRepository.findAllById(topic.getMajorId()));
    return topic;
  }

}
