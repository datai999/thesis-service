package com.thesis.service.topic.service;

import java.util.List;
import java.util.stream.Collectors;
import com.thesis.service.common.service.AbstractBaseService;
import com.thesis.service.person.service.StudentService;
import com.thesis.service.person.service.TeacherService;
import com.thesis.service.topic.model.TpTopicAssignTable;
import com.thesis.service.topic.repository.TpTopicAssignRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicAssignService
    extends AbstractBaseService<TpTopicAssignTable, TpTopicAssignRepository> {

  final StudentService studentService;
  final TeacherService teacherService;

  final TopicService topicService;
  final CouncilService councilService;

  public List<TpTopicAssignTable> findByTeacherCode(
      String teacherCode,
      String sort,
      Boolean isDescend) {
    return super.repository.findByTeacherCode(teacherCode, sort, isDescend);
  }

  public List<TpTopicAssignTable> searchIlikeTopicName(
      String value,
      String sort,
      Boolean isDescend) {
    return super.repository.searchIlikeTopicName(value, sort, isDescend);
  }

  public List<TpTopicAssignTable> findByTopicIdOrderSemester(long topicId) {
    return super.repository.findByTopicIdOrderSemester(topicId)
        .stream().collect(Collectors.toList());
  }

}
