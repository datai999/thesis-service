package com.thesis.service.topic.service;

import com.thesis.service.common.service.ABaseService;
import com.thesis.service.person.service.StudentService;
import com.thesis.service.person.service.TeacherService;
import com.thesis.service.topic.model.TpTopicAssignTable;
import com.thesis.service.topic.repository.TpTopicAssignRepository;

import org.springframework.stereotype.Service;

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

    topicAssign.setById(studentService, "executeStudent");
    topicAssign.setById(teacherService, "guideTeacher", "reviewTeacher");

    return topicAssign;
  }

  @Override
  public <S extends TpTopicAssignTable> S save(S entity) {
    entity.mapId();
    entity.setTopic(topicService.save(entity.getTopic()));
    return mainRepository.save(entity);
  }

}
