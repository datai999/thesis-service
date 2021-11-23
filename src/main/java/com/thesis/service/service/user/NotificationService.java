package com.thesis.service.service.user;

import java.util.Collection;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.thesis.service.dto.user.response.NotificationResponse;
import com.thesis.service.dto.user.response.UserNotificationResponse;
import com.thesis.service.model.topic.TopicGuideTeacherTable;
import com.thesis.service.model.topic.TopicStudentTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.model.user.NotificationTable;
import com.thesis.service.model.user.UserTable;
import com.thesis.service.repository.topic.TopicRepository;
import com.thesis.service.repository.user.NotificationRepository;
import com.thesis.service.service.ABaseService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService extends ABaseService<NotificationTable, NotificationRepository> {

  private final TopicRepository topicRepository;

  @Override
  protected Class<?> getResponseClass() {
    return NotificationResponse.class;
  }

  public void notify(Collection<UserTable> receivers, String message) {
    if (CollectionUtils.isEmpty(receivers))
      return;
    var notifications = receivers.parallelStream().distinct()
        .map(user -> new NotificationTable().setReceiver(user).setSeen(false).setMessage(message))
        .collect(Collectors.toList());
    super.repository.saveAll(notifications);
  }

  public void notifyWithMessageSource(Collection<UserTable> receiver, String code, Object... args) {
    this.notify(receiver, super.messageSource.getMessage(code, args));
  }

  @Transactional
  public void notifyTopics(Collection<TopicTable> topics, String message) {
    if (CollectionUtils.isEmpty(topics))
      return;
    topics.parallelStream().forEach(
        topic -> {
          var studentMessage = super.messageSource.toATagStudent(topic);
          var guideTeachersMessage = super.messageSource.toATagGuideTeacher(topic);
          var reviewTeachersMessage = super.messageSource.toATagReviewTeacher(topic);
          this.notify(topic.getStudents().stream()
              .map(TopicStudentTable::getStudent).collect(Collectors.toList()),
              String.format("%s %s", studentMessage, message));
          this.notify(topic.getGuideTeachers().stream()
              .map(TopicGuideTeacherTable::getGuideTeacher).collect(Collectors.toList()),
              String.format("%s %s", guideTeachersMessage, message));
          this.notify(topic.getReviewTeachers(),
              String.format("%s %s", reviewTeachersMessage, message));
        });
  }

  public void notifyTopicIds(Collection<Long> topicIds, String message) {
    if (CollectionUtils.isEmpty(topicIds))
      return;
    this.notifyTopics(topicRepository.findAllById(topicIds), message);
  }

  public UserNotificationResponse findByRequestUser(int limit) {
    var all = super.repository.findByIdDesc(super.getAuth().getId(), limit);
    var unseen = super.repository.findByUnseenIdDesc(super.getAuth().getId(), limit);
    return new UserNotificationResponse()
        .setAll(super.mapper.map(all, NotificationResponse.class))
        .setUnseen(super.mapper.map(unseen, NotificationResponse.class));
  }

  public Object seen(Long id) {
    var notification = super.repository.findById(id).orElseThrow();
    super.repository.save(notification.setSeen(true));
    return true;
  }

}
