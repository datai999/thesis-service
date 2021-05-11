package com.thesis.service.topic.service;

import java.util.stream.Collectors;

import com.thesis.service.common.service.ABaseService;
import com.thesis.service.common.service.IService;
import com.thesis.service.person.repository.PsStudentRepository;
import com.thesis.service.person.repository.PsTeacherRepository;
import com.thesis.service.topic.model.TpTopicAssignTable;
import com.thesis.service.topic.repository.TpTopicAssignRepository;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicAssignService extends ABaseService<TpTopicAssignTable, TpTopicAssignRepository>
    implements IService<TpTopicAssignTable> {

  final PsStudentRepository studentRepository;
  final PsTeacherRepository teacherRepository;

  final TopicService topicService;

  public TpTopicAssignTable build(TpTopicAssignTable topicAssign) {
    topicAssign.setTopic(topicService.build(topicAssign.getTopic()));
    if (!CollectionUtils.isEmpty(topicAssign.getExecuteStudentId()))
      topicAssign.setExecuteStudent(
          studentRepository.findAllById(topicAssign.getExecuteStudentId()).stream().collect(Collectors.toSet()));
    if (!CollectionUtils.isEmpty(topicAssign.getGuideTeacherId()))
      topicAssign.setGuideTeacher(
          teacherRepository.findAllById(topicAssign.getGuideTeacherId()).stream().collect(Collectors.toSet()));
    return topicAssign;
  }

}
