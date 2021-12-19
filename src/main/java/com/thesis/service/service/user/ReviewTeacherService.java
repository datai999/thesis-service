package com.thesis.service.service.user;

import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.thesis.service.constant.MessageCode;
import com.thesis.service.dto.user.response.ReviewTeacherResponse;
import com.thesis.service.model.system.SemesterTable;
import com.thesis.service.model.system.SubjectDepartmentTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.topic.TopicRepository;
import com.thesis.service.repository.user.UserRepository;
import com.thesis.service.service.MessageSourceService;
import com.thesis.service.service.system.SemesterService;
import com.thesis.service.service.topic.TopicService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewTeacherService {

  private final TopicRepository topicRepository;
  private final UserRepository userRepository;
  private final MessageSourceService messageSourceService;
  private final NotificationService notificationService;
  private final TopicService topicService;
  private final UserService userService;
  private final SemesterService semesterService;

  @Transactional
  public Object assignReview(TopicTable entity) {
    var result = topicService.update(entity);

    var actorTag = messageSourceService.toUserTag(topicService.getAuth());
    var teacherTags = entity.getReviewTeachers().stream()
        .map(messageSourceService::toUserTag).collect(Collectors.toList());
    var topicTag = messageSourceService.toTopicTag(entity);

    var teacherTagMsg = String.join(", ", teacherTags);

    var message2GuideTeacher = messageSourceService.getMessage(
        MessageCode.Assign.REVIEW_ACTION, actorTag, teacherTagMsg, topicTag);
    var message2ReviewTeacher = messageSourceService.getMessage(
        MessageCode.Assign.YOU_REVIEW, actorTag, topicTag);
    var studentTopicExecuteTag = messageSourceService
        .topicExecuteTag(messageSourceService.getMessage(MessageCode.Topic.YOUR));
    var message2Student = messageSourceService.getMessage(
        MessageCode.Assign.REVIEW_ACTION, actorTag, teacherTagMsg, studentTopicExecuteTag);

    notificationService.notify(entity.getReviewTeachers(), message2ReviewTeacher);
    notificationService.notify(entity.getTopicGuideTeachers(), message2GuideTeacher);
    notificationService.notify(entity.getTopicStudents(), message2Student);

    return result;
  }

  public Object getTopicReview(long subjectDepartmentId, String semesterName) {

    var topicExample = new TopicTable();
    topicExample.setSubjectDepartment(new SubjectDepartmentTable(subjectDepartmentId));
    topicExample.setSemester(new SemesterTable(semesterName));

    var queryResult = topicRepository.findAll(Example.of(topicExample));

    queryResult = queryResult.parallelStream()
        .filter(e -> CollectionUtils.isNotEmpty(e.getStudents())
            && e.getStudents().stream()
                .anyMatch(student -> ObjectUtils.defaultIfNull(student.getMidPass(), false)))
        .collect(Collectors.toList());

    return topicService.map(queryResult);
  }

  public Object getTopic(long userId, String semesterName) {
    var user = userRepository.findById(userId).orElseThrow();
    var response = user.getTopicReviews().parallelStream()
        .filter(e -> semesterName.equals(e.getSemester().getName())).collect(Collectors.toList());
    return topicService.map(response);
  }

  public Object getTeacher(UserTable entity, Sort sort) {
    var currentSemester = semesterService.getCurrentSemester();
    var queryResult = userService.shareFindByExample(entity, sort);
    return queryResult.stream()
        .map(teacher -> new ReviewTeacherResponse(teacher, currentSemester.getId()))
        .collect(Collectors.toList());
  }

}
