package com.thesis.service.service.topic;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import com.thesis.service.advice.BusinessException;
import com.thesis.service.constant.TopicRole;
import com.thesis.service.dto.topic.resposne.TopicResponse;
import com.thesis.service.model.topic.TopicAssignTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.repository.system.SemesterRepository;
import com.thesis.service.repository.system.SubjectDepartmentRepository;
import com.thesis.service.repository.topic.TopicAssignRepository;
import com.thesis.service.repository.topic.TopicRepository;
import com.thesis.service.repository.user.UserRepository;
import com.thesis.service.service.ABaseService;
import com.thesis.service.service.system.SemesterService;
import com.thesis.service.service.user.NotificationService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicService extends ABaseService<TopicTable, TopicRepository> {

  private final TopicAssignRepository topicAssignRepository;
  private final UserRepository userRepository;
  private final SemesterRepository semesterRepository;
  private final SubjectDepartmentRepository subjectDepartmentRepository;

  private final NotificationService notificationService;
  private final SemesterService semesterService;

  @Override
  protected Function<TopicTable, ?> mapping() {
    return TopicResponse::new;
  }

  @Override
  public Object create(TopicTable entity) {
    var semester = semesterRepository.findCurrentSemester();
    entity.setSemester(semester).setSubjectDepartment(this.getAuth().getSubjectDepartment());
    return this.repository.save(entity).getId();
  }

  public Object studentRegister(Long topicId) {
    var topic = super.repository.findById(topicId).orElseThrow();
    if (!semesterService.allowStudentRegisterCancelTopic()) {
      throw BusinessException.code("semester.004",
          topic.getSemester().getName(),
          topic.getSemester().getRegisterTopicStart(),
          topic.getSemester().getRegisterTopicEnd());
    }
    if (topic.getStudents().size() >= topic.getMaxStudentTake()) {
      throw BusinessException.code("topic.001", topic.getMultiName("[%s - %s]"));
    }
    var topicAssign = new TopicAssignTable().setTopic(topic).setStudent(super.getAuth());
    topicAssignRepository.save(topicAssign);
    return true;
  }

  public Object findByUserAndRole(Long userId, TopicRole role, Sort sort) {

    var user = Objects.nonNull(userId)
        ? userRepository.findById(userId).orElseThrow()
        : super.getAuth();

    List<TopicAssignTable> response = null;
    if (TopicRole.STUDENT.equals(role)) {
      response = topicAssignRepository.findByStudent(user, sort);
    } else if (TopicRole.GUIDE_TEACHER.equals(role)) {
      response = topicAssignRepository.findByGuideTeacher(user, sort);
    } else
      response = topicAssignRepository.findByReviewTeacher(user, sort);

    return super.mapper.map(response, TopicResponse::new);
  }

  public Object studentCancel(Long topicId) {
    var topic = super.repository.findById(topicId).orElseThrow();
    if (!semesterService.allowStudentRegisterCancelTopic()) {
      throw BusinessException.code("semester.004",
          topic.getSemester().getName(),
          topic.getSemester().getRegisterTopicStart(),
          topic.getSemester().getRegisterTopicEnd());
    }
    this.topicAssignRepository.studentCancel(topicId, super.getAuth().getId());
    String message = super.getMessage(
        "student.cancelTopic",
        super.getAuth().getFullName(),
        topic.getMultiName("[%s,%s]"));
    this.notificationService.notify(topic.getStudents(), message);
    return true;
  }

  public Object findNeedAssignCouncil(long subjectDepartmentId, Sort sort) {
    var response = super.repository.findNeedAssignCouncil(subjectDepartmentId, sort);
    return super.map(response);
  }

}
