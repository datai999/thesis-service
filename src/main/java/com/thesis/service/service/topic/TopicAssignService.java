package com.thesis.service.service.topic;

import java.util.List;
import java.util.stream.Collectors;
import com.thesis.service.model.topic.TopicAssignTable;
import com.thesis.service.repository.topic.TopicAssignRepository;
import com.thesis.service.service.AbstractBaseService;
import com.thesis.service.service.person.StudentService;
import com.thesis.service.service.person.TeacherService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicAssignService
    extends AbstractBaseService<TopicAssignTable, TopicAssignRepository> {

  final StudentService studentService;
  final TeacherService teacherService;

  final TopicService topicService;
  final CouncilService councilService;

  public List<TopicAssignTable> findByTeacherCode(
      String teacherCode,
      String sort,
      Boolean isDescend) {
    return super.repository.findByTeacherCode(teacherCode, sort, isDescend);
  }

  public List<TopicAssignTable> searchIlikeTopicName(
      String value,
      String sort,
      Boolean isDescend) {
    return super.repository.searchIlikeTopicName(value, sort, isDescend);
  }

  public List<TopicAssignTable> findByTopicIdOrderSemester(long topicId) {
    return super.repository.findByTopicIdOrderSemester(topicId)
        .stream().collect(Collectors.toList());
  }

}
