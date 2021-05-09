package com.thesis.service.topic.service.impl;

import java.util.stream.Collectors;

import com.thesis.service.common.service.AbstractBaseService;
import com.thesis.service.person.repository.PsStudentRepository;
import com.thesis.service.person.repository.PsTeacherRepository;
import com.thesis.service.topic.model.TpTopicAssignTable;
import com.thesis.service.topic.repository.TpTopicAssignRepository;
import com.thesis.service.topic.service.TopicAssignService;
import com.thesis.service.topic.service.TopicService;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import lombok.RequiredArgsConstructor;

@Primary
@Service
@RequiredArgsConstructor
public class TopicAssignServiceImpl extends AbstractBaseService<TpTopicAssignTable, TpTopicAssignRepository>
    implements TopicAssignService {

  final PsStudentRepository studentRepository;
  final PsTeacherRepository teacherRepository;

  final TopicService topicService;

  @Override
  public TpTopicAssignTable build(TpTopicAssignTable topicAssign) {
    topicAssign.setTopic(topicService.buildFull(topicAssign.getTopic()));
    if (!CollectionUtils.isEmpty(topicAssign.getExecuteStudentId()))
      topicAssign.setExecuteStudent(
          studentRepository.findAllById(topicAssign.getExecuteStudentId()).stream().collect(Collectors.toSet()));
    if (!CollectionUtils.isEmpty(topicAssign.getGuideTeacherId()))
      topicAssign.setGuideTeacher(
          teacherRepository.findAllById(topicAssign.getGuideTeacherId()).stream().collect(Collectors.toSet()));
    return topicAssign;
  }

}
