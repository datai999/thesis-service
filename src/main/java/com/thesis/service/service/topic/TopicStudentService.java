package com.thesis.service.service.topic;

import java.util.List;
import javax.transaction.Transactional;
import com.thesis.service.constant.MessageCode;
import com.thesis.service.dto.topic.resposne.TopicStudentResponse;
import com.thesis.service.model.topic.TopicStudentTable;
import com.thesis.service.repository.topic.TopicStudentRepository;
import com.thesis.service.service.ABaseService;
import com.thesis.service.service.user.NotificationService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicStudentService extends ABaseService<TopicStudentTable, TopicStudentRepository> {

  private final NotificationService notificationService;

  @Override
  protected Class<?> getResponseClass() {
    return TopicStudentResponse.class;
  }

  @Override
  @Transactional
  public Object update(TopicStudentTable entity) {
    var existEntity = this.repository.findById(entity.getId()).orElseThrow();
    entity.setCreatedAt(existEntity.getCreatedAt());
    var response = this.repository.save(entity);

    var messageCode = ObjectUtils.defaultIfNull(response.getMidPass(), false)
        ? MessageCode.Mark.MID_PASS
        : MessageCode.Mark.MID_FAIL;
    var topicTag = super.messageSource.toTopicTag(response.getTopic());
    var teacherTag = super.messageSource.toUserTag(super.getAuth());
    var studentTag = super.messageSource.toUserTag(response.getStudent());
    var message = super.messageSource.getMessage(messageCode, teacherTag, studentTag, topicTag);
    notificationService.notifyTopics(List.of(response.getTopic()), message);
    return this.map(response);
  }

}
