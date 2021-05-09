package com.thesis.service.topic.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.thesis.service.common.controller.AbstractBaseController;
import com.thesis.service.topic.model.TpTopicAssignTable;
import com.thesis.service.topic.service.TopicAssignService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/topic/assign")
@RequiredArgsConstructor
public class TopicAssignController extends AbstractBaseController<TpTopicAssignTable, TopicAssignService> {

  @Override
  @GetMapping
  public List<TpTopicAssignTable> findAll() {
    return super.findAll().stream().map(super.service::build).collect(Collectors.toList());
  }

}
