package com.thesis.service.service.user;

import java.util.stream.Collectors;
import com.thesis.service.advice.BusinessException;
import com.thesis.service.constant.MessageCode;
import com.thesis.service.dto.user.request.StudentCanAssignTopicRequest;
import com.thesis.service.model.topic.TopicStudentTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.topic.TopicRepository;
import com.thesis.service.repository.topic.TopicStudentRepository;
import com.thesis.service.repository.user.UserRepository;
import com.thesis.service.service.MessageSourceService;
import com.thesis.service.service.system.SemesterService;
import com.thesis.service.service.topic.TopicService;
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
  private final MessageSourceService messageSourceService;
  private final NotificationService notificationService;
  private final SemesterService semesterService;
  private final TopicService topicService;
  private final UserService userService;

  public Object getTopic(long userId) {
    var user = userRepository.findById(userId).orElseThrow();
    var topicExecutes = user.getTopicExecutes().stream()
        .map(TopicStudentTable::getTopic).collect(Collectors.toList());
    return topicService.map(topicExecutes);
  }

  public Object registerTopic(long studentId, long topicId) {

    var topic = topicRepository.findById(topicId).orElseThrow();

    if (!semesterService.inRegisterTopicTime(topic.getThesis())) {
      throw BusinessException.code(
          MessageCode.Semester.OVERDUE_TOPIC_REGISTER,
          topic.getSemester().getName());
    }

    if (topic.getStudents().size() >= topic.getMaxStudentTake()) {
      throw BusinessException.code(MessageCode.Topic.FULL_MEMBER, topic.getMultiName());
    }

    var student = userRepository.findById(studentId).orElseThrow();

    if (topic.getTopicStudents().stream().anyMatch(e -> e.getId().equals(studentId))) {
      throw BusinessException.code(MessageCode.Topic.EXIST_STUDENT,
          student.getCode(), topic.getMultiName());
    }

    topicStudentRepository.save(new TopicStudentTable().setTopic(topic).setStudent(student));

    String message = messageSource.getMessage(
        MessageCode.Student.REGISTER_TOPIC,
        messageSourceService.toUserTag(student),
        messageSourceService.toTopicTag(topic));
    this.notificationService.notify(topic.getTopicStudents(), message);

    return true;
  }

  public Object allowRegisterTopic(long studentId, boolean thesis) {
    var student = userRepository.findById(studentId).orElseThrow();
    var haveTopicInSemester = student.getTopicExecutes().stream()
        .anyMatch(e -> e.getTopic().getSemester().isCurrent());
    return !haveTopicInSemester && semesterService.inRegisterTopicTime(thesis);
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
        messageSourceService.toUserTag(topicStudent.getStudent()),
        messageSourceService.toTopicTag(topicStudent.getTopic()));
    var anotherStudents = topicStudent.getTopic().getTopicStudents().stream()
        .filter(e -> !e.getId().equals(studentId)).collect(Collectors.toList());
    this.notificationService.notify(anotherStudents, message);
    return true;
  }

  public boolean doneOutline(UserTable student) {
    return student.getTopicExecutes().stream()
        .anyMatch(e -> !e.getTopic().getSemester().isCurrent()
            && !e.getTopic().getThesis()
            && e.getMidPass());
  }

  public boolean doneOutline(long studentId) {
    var student = userRepository.findById(studentId).orElseThrow();
    return this.doneOutline(student);
  }

  public Object getCurrentTopic(long studentId) {
    var user = userRepository.findById(studentId).orElseThrow();
    var topic = user.getTopicExecutes().stream()
        .filter(e -> e.getTopic().getSemester().isCurrent())
        .map(TopicStudentTable::getTopic)
        .findFirst();
    return topic.isPresent() ? topicService.map(topic.get()) : null;
  }

  public Object canAssignTopic(StudentCanAssignTopicRequest request) {
    var students = userRepository.findStudentInEducationMethodAndMajor(
        request.getEducationMethodIds(), request.getMajorIds());
    var response = !request.isThesis()
        ? students
        : students.stream().filter(this::doneOutline).collect(Collectors.toList());
    return userService.map(response);
  }

}
