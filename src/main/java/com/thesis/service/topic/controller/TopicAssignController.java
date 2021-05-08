package com.thesis.service.topic.controller;

import java.util.stream.Collectors;

import com.thesis.service.common.controller.EntityController;
import com.thesis.service.common.dto.response.WrapResponse;
import com.thesis.service.person.repository.PsStudentRepository;
import com.thesis.service.person.repository.PsTeacherRepository;
import com.thesis.service.topic.model.TpTopicAssignTable;
import com.thesis.service.topic.repository.TpTopicAssignRepository;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/topic/assign")
@RequiredArgsConstructor
public class TopicAssignController extends EntityController<TpTopicAssignTable, TpTopicAssignRepository> {

  private final PsStudentRepository studentRepository;
  private final PsTeacherRepository teacherRepository;

  @Override
  public String declareBaseService() {
    return "topicAssign";
  }

  @Override
  @GetMapping
  public Object findAll() {
    var result = super.repository.findAll();
    result.parallelStream().forEach(x -> {
      if (!CollectionUtils.isEmpty(x.getExecuteStudentId()))
        x.setExecuteStudent(
            studentRepository.findAllById(x.getExecuteStudentId()).stream().collect(Collectors.toSet()));
      if (!CollectionUtils.isEmpty(x.getGuideTeacherId()))
        x.setGuideTeacher(teacherRepository.findAllById(x.getGuideTeacherId()).stream().collect(Collectors.toSet()));
    });
    return WrapResponse.data(result);
  }

}
