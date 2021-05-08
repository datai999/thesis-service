package com.thesis.service.topic.controller;

import com.thesis.service.common.controller.EntityController;
import com.thesis.service.topic.model.TpTopicAssignTable;
import com.thesis.service.topic.repository.TpTopicAssignRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/topic/assign")
@RequiredArgsConstructor
public class TopicAssignController extends EntityController<TpTopicAssignTable, TpTopicAssignRepository> {

  @Override
  public String declareBaseService() {
    return "topicAssign";
  }

}
