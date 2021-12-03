package com.thesis.service.service.user;

import java.util.stream.Collectors;
import com.thesis.service.advice.BusinessException;
import com.thesis.service.constant.MessageCode;
import com.thesis.service.model.topic.TopicStudentTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.topic.TopicRepository;
import com.thesis.service.repository.topic.TopicStudentRepository;
import com.thesis.service.repository.user.UserRepository;
import com.thesis.service.service.MessageSourceService;
import com.thesis.service.service.system.SemesterService;
import com.thesis.service.service.topic.TopicService;
import com.thesis.service.utils.HtmlUtil;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

  private final MessageSourceService messageSource;
  private final TopicRepository topicRepository;
  private final TopicStudentRepository topicStudentRepository;
  private final UserRepository userRepository;
  private final SemesterService semesterService;
  private final TopicService topicService;
  private final NotificationService notificationService;

  public Object getTopic(long userId) {
    var user = userRepository.findById(userId).orElseThrow();
    var topicExecutes = user.getTopicExecutes().stream()
        .map(TopicStudentTable::getTopic).collect(Collectors.toList());
    return topicService.map(topicExecutes);
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

    topicStudentRepository.save(new TopicStudentTable().setTopic(topic).setStudent(student));

    String message = messageSource.getMessage(
        MessageCode.Student.REGISTER_TOPIC,
        student.getFullName(),
        topic.getMultiName());
    this.notificationService.notify(topic.getTopicStudents(), message);

    return true;
  }

  public Object allowRegisterTopic(long studentId) {
    var student = userRepository.findById(studentId).orElseThrow();
    var haveTopicInSemester = student.getTopicExecutes().stream()
        .anyMatch(e -> e.getTopic().getSemester().isCurrent());
    var semesterAllow = semesterService.allowStudentRegisterCancelTopic();
    return !haveTopicInSemester && semesterAllow;
  }

  public Object cancelTopic(long studentId, long topicId) {
    var entity = new TopicStudentTable()
        .setTopic(new TopicTable(topicId))
        .setStudent(new UserTable(studentId));
    var topicStudent = topicStudentRepository.findAll(Example.of(entity))
        .stream().findFirst().orElseThrow();
    topicStudentRepository.delete(topicStudent);

    String message = messageSource.getMessage(
        MessageCode.Student.CANCEL_TOPIC,
        HtmlUtil.toUserTag(topicStudent.getStudent()),
        HtmlUtil.toTopicTag(topicStudent.getTopic()));
    var anotherStudents = topicStudent.getTopic().getTopicStudents().stream()
        .filter(e -> !e.getId().equals(studentId)).collect(Collectors.toList());
    this.notificationService.notify(anotherStudents, message);
    return true;
  }

  public Object doneOutline(long studentId) {
    var student = userRepository.findById(studentId).orElseThrow();
    return student.getTopicExecutes().stream()
        .anyMatch(e -> !e.getTopic().getSemester().isCurrent()
            && !e.getTopic().getThesis()
            && e.getMidPass());
  }

}
