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
  final CouncilService councilService;

  @Override
  protected void preBuild(TpTopicAssignTable topicAssign) {
    topicAssign.setTopic(topicService.build(topicAssign.getTopic()));
    topicAssign.setCouncil(councilService.build(topicAssign.getCouncil()));

    topicAssign.setById(studentService, "executeStudent");
    topicAssign.setById(teacherService, "guideTeacher", "reviewTeacher");
  }

  @Override
  public <S extends TpTopicAssignTable> S save(S entity) {
    entity.mapId();
    entity.setTopic(topicService.save(entity.getTopic()));
    return mainRepository.save(entity);
  }

}
