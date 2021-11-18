package com.thesis.service.service.user;

import com.thesis.service.advice.BusinessException;
import com.thesis.service.constant.MessageCode;
import com.thesis.service.repository.topic.TopicRepository;
import com.thesis.service.repository.user.UserRepository;
import com.thesis.service.service.MessageSourceService;
import com.thesis.service.service.system.SemesterService;
import com.thesis.service.service.topic.TopicService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

  private final MessageSourceService messageSource;
  private final TopicRepository topicRepository;
  private final UserRepository userRepository;
  private final SemesterService semesterService;
  private final TopicService topicService;
  private final NotificationService notificationService;

  public Object getTopic(long userId) {
    var user = userRepository.findById(userId).orElseThrow();
    return topicService.map(user.getTopicExecutes());
  }

  public Object registerTopic(long studentId, long topicId) {

    var topic = topicRepository.findById(topicId).orElseThrow();

    if (!semesterService.allowStudentRegisterCancelTopic()) {
      throw BusinessException.code(
          MessageCode.Semester.OVERDUE_TOPIC_REGISTER,
          topic.getSemester().getName());
    }

    if (topic.getStudents().size() >= topic.getMaxStudentTake()) {
      throw BusinessException.code(MessageCode.Topic.FULL_MEMBER, topic.getMultiName());
    }

    var student = userRepository.findById(studentId).orElseThrow();

    if (topic.getStudents().stream().anyMatch(e -> e.getId().equals(studentId))) {
      throw BusinessException.code(MessageCode.Topic.EXIST_STUDENT,
          student.getCode(), topic.getMultiName());
    }

    topic.getStudents().add(student);
    topicRepository.save(topic);

    String message = messageSource.getMessage(
        MessageCode.Student.REGISTER_TOPIC,
        student.getFullName(),
        topic.getMultiName());
    this.notificationService.notify(topic.getStudents(), message);

    return true;
  }

  public Object allowRegisterTopic(long studentId) {
    var student = userRepository.findById(studentId).orElseThrow();
    var currentSemester = semesterService.getCurrentSemester();
    var haveTopicInSemester = student.getTopicExecutes().stream()
        .anyMatch(e -> currentSemester.getId().equals(e.getSemester().getId()));
    var semesterAllow = semesterService.allowStudentRegisterCancelTopic();
    return !haveTopicInSemester && semesterAllow;
  }

  public Object cancelTopic(long studentId, long topicId) {
    var topic = topicRepository.findById(topicId).orElseThrow();

    if (!semesterService.allowStudentRegisterCancelTopic()) {
      throw BusinessException.code(
          MessageCode.Semester.OVERDUE_TOPIC_CANCEL,
          topic.getSemester().getName());
    }

    var student = userRepository.findById(studentId).orElseThrow();

    topic.getStudents().removeIf(e -> e.getId().equals(studentId));
    topicRepository.save(topic);

    String message = messageSource.getMessage(
        MessageCode.Student.CANCEL_TOPIC,
        student.getFullName(),
        topic.getMultiName());
    this.notificationService.notify(topic.getStudents(), message);
    return true;
  }

}
