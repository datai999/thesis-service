package com.thesis.service.service.topic;

import java.util.stream.Collectors;
import com.thesis.service.model.topic.TopicAssignTable;
import com.thesis.service.model.topic.TopicTable;
import com.thesis.service.repository.topic.TopicAssignRepository;
import com.thesis.service.service.ABaseService;
import com.thesis.service.service.user.NotificationService;
import com.thesis.service.utils.HtmlUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicAssignService
    extends ABaseService<TopicAssignTable, TopicAssignRepository> {

  private final NotificationService notificationService;

  @Transactional
  public Object assignReview(TopicTable topic) {
    super.repository.removeAllReview(topic.getId());
    var newAssign = topic.getReviewTeachers().parallelStream().distinct().map(
        reviewTeacher -> new TopicAssignTable().setTopic(topic).setReviewTeacher(reviewTeacher))
        .collect(Collectors.toList());
    super.repository.saveAll(newAssign);

    var topicMultiName = topic.getMultiName("[%s,%s]");
    String studentsMessage = super.messageSource.getMessage(
        "topic.assign.review.forStudent",
        HtmlUtil.toATag("/my/topics/execute", topicMultiName));
    String guideTeachersMessage = super.messageSource.getMessage(
        "topic.assign.review.forGuideTeacher",
        HtmlUtil.toATag("/my/topics/guide", topicMultiName));
    String reviewTeachersMessage = super.messageSource.getMessage(
        "topic.assign.review.forReviewTeacher",
        HtmlUtil.toATag("/my/topics/review", topicMultiName));
    this.notificationService.notify(topic.getGuideTeachers(), studentsMessage);
    this.notificationService.notify(topic.getGuideTeachers(), guideTeachersMessage);
    this.notificationService.notify(topic.getGuideTeachers(), reviewTeachersMessage);
    return true;
  }

}
