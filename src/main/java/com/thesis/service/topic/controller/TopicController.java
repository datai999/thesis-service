package com.thesis.service.topic.controller;

import java.util.List;

import com.thesis.service.common.controller.AbstractBaseController;
import com.thesis.service.topic.model.TpTopicTable;
import com.thesis.service.topic.service.TopicService;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/topic")
@RequiredArgsConstructor
public class TopicController extends AbstractBaseController<TpTopicTable, TopicService> {

  @Override
  @GetMapping
  public List<TpTopicTable> findAll() {
    var result = super.service.findAll();
    result.parallelStream().forEach(x -> {
      if (!CollectionUtils.isEmpty(x.getMajorId()))
        x.setMajor(super.constRepository.findAllById(x.getMajorId()));
    });
    return result;
  }

}
