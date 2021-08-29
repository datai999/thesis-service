package com.thesis.service.topic.service;

import java.util.List;
import java.util.stream.Collectors;
import com.thesis.service.common.service.ABaseService;
import com.thesis.service.person.service.StudentService;
import com.thesis.service.person.service.TeacherService;
import com.thesis.service.topic.model.TpTopicAssignTable;
import com.thesis.service.topic.repository.TpTopicAssignRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicAssignService extends ABaseService<TpTopicAssignTable, TpTopicAssignRepository>
    implements TpTopicAssignRepository {

  final StudentService studentService;
  final TeacherService teacherService;

  final TopicService topicService;
  final CouncilService councilService;

  @Override
  protected void preBuild(TpTopicAssignTable topicAssign) {
    topicAssign.setCouncil(councilService.build(topicAssign.getCouncil()));

    topicAssign.setById(studentService, "executeStudent");
    topicAssign.setById(teacherService, "guideTeacher", "reviewTeacher");
  }

  @Override
  public <S extends TpTopicAssignTable> S save(S entity) {
    entity.mapId();
    return mainRepository.save(entity);
  }

  @Override
  public List<TpTopicAssignTable> findByTeacherCode(
      String teacherCode,
      String sort,
      Boolean isDescend) {
    var response = super.mainRepository.findByTeacherCode(teacherCode, sort, isDescend);
    response.parallelStream().forEach(super::build);
    return response;
  }

  @Override
  public List<TpTopicAssignTable> searchIlikeTopicName(
      String value,
      String sort,
      Boolean isDescend) {
    var searchResponse = super.mainRepository.searchIlikeTopicName(value, sort, isDescend);
    searchResponse.parallelStream().forEach(super::build);
    return searchResponse;
  }

  @Override
  public List<TpTopicAssignTable> findByTopicIdOrderSemester(long topicId) {
    return super.mainRepository.findByTopicIdOrderSemester(topicId)
        .stream().map(super::build).collect(Collectors.toList());
  }

}
