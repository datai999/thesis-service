package com.thesis.service.topic.controller;

import com.thesis.service.common.controller.EntityController;
import com.thesis.service.common.dto.response.WrapResponse;
import com.thesis.service.topic.model.TpTopicTable;
import com.thesis.service.topic.repository.TpTopicRepository;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
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

  @Override
  @GetMapping
  public Object findAll() {
    var result = super.repository.findAll();
    result.parallelStream().forEach(x -> {
      if (!CollectionUtils.isEmpty(x.getMajorId()))
        x.setMajor(super.constRepository.findAllById(x.getMajorId()));
    });
    return WrapResponse.data(result);
  }

}
