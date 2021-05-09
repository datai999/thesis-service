package com.thesis.service.topic.service.impl;

import java.util.Objects;

import javax.annotation.PostConstruct;

import com.thesis.service.br.repository.BrConstDataRepository;
import com.thesis.service.common.service.AbstractBaseService;
import com.thesis.service.topic.model.TpTopicTable;
import com.thesis.service.topic.repository.TpTopicRepository;
import com.thesis.service.topic.service.TopicService;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import lombok.RequiredArgsConstructor;

@Primary
@Service
@RequiredArgsConstructor
public class TopicServiceImpl extends AbstractBaseService<TpTopicTable, TpTopicRepository> implements TopicService {

  final TpTopicRepository repository;
  final BrConstDataRepository constRepository;

  @PostConstruct
  private void setRepository() {
    super.setMainRepository(repository);
  }

  @Override
  public TpTopicTable buildFull(TpTopicTable topic) {
    if (Objects.isNull(topic))
      return null;
    if (!CollectionUtils.isEmpty(topic.getMajorId()))
      topic.setMajor(constRepository.findAllById(topic.getMajorId()));
    return topic;
  }

}
