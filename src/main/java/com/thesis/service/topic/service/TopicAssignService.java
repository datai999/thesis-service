package com.thesis.service.topic.service;

import java.util.stream.Collectors;

import com.thesis.service.common.service.ABaseService;
import com.thesis.service.person.service.StudentService;
import com.thesis.service.person.service.TeacherService;
import com.thesis.service.topic.model.TpTopicAssignTable;
import com.thesis.service.topic.repository.TpTopicAssignRepository;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicAssignService extends ABaseService<TpTopicAssignTable, TpTopicAssignRepository> {

  final StudentService studentService;
  final TeacherService teacherService;

  final TopicService topicService;

  @Override
  public TpTopicAssignTable build(TpTopicAssignTable topicAssign) {
    topicAssign.setTopic(topicService.build(topicAssign.getTopic()));
    if (!CollectionUtils.isEmpty(topicAssign.getExecuteStudentId()))
      topicAssign.setExecuteStudent(
          studentService.findAllById(topicAssign.getExecuteStudentId()).stream().collect(Collectors.toSet()));
    if (!CollectionUtils.isEmpty(topicAssign.getGuideTeacherId()))
      topicAssign.setGuideTeacher(
          teacherService.findAllById(topicAssign.getGuideTeacherId()).stream().collect(Collectors.toSet()));
    return topicAssign;
  }

  @Override
  public <S extends TpTopicAssignTable> S save(S entity) {
    entity.setTopic(topicService.save(entity.getTopic()));

    if (!CollectionUtils.isEmpty(entity.getExecuteStudent()))
      entity.setExecuteStudent(studentService.saveAll(entity.getExecuteStudent()).stream().collect(Collectors.toSet()));

    if (!CollectionUtils.isEmpty(entity.getGuideTeacher()))
      entity.setGuideTeacher(teacherService.saveAll(entity.getGuideTeacher()).stream().collect(Collectors.toSet()));

    return mainRepository.save(entity);
  }

}
