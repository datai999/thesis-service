package com.thesis.service.topic.controller;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.thesis.service.common.controller.ABaseController;
import com.thesis.service.topic.model.TpTopicAssignTable;
import com.thesis.service.topic.service.TopicAssignService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/topic/assign")
@RequiredArgsConstructor
public class TopicAssignController extends ABaseController<TpTopicAssignTable, TopicAssignService> {

  @GetMapping("/search/teacher")
  public List<TpTopicAssignTable> findByTeacherCode(@RequestParam @NotBlank String code,
      @RequestParam(defaultValue = "semester") String sort, @RequestParam(defaultValue = "true") boolean descend) {
    return super.service.findByTeacherCode(code, sort, descend);
  }

  @GetMapping("/search/topic")
  public Object search(@RequestParam String value, @RequestParam(defaultValue = "semester") String sort,
      @RequestParam(defaultValue = "true") boolean descend) {
    return super.service.searchIlikeTopicName(value, sort, descend);
  }

}
