package com.thesis.service.topic.controller;

import com.thesis.service.common.controller.EntityController;
import com.thesis.service.topic.model.TpTopicTable;
import com.thesis.service.topic.repository.TpTopicRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/topic")
@RequiredArgsConstructor
public class TopicController extends EntityController<TpTopicTable, TpTopicRepository> {

  @Override
  public String declareBaseService() {
    return "topic";
  }

}
