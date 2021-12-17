package com.thesis.service.service;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Supplier;
import com.thesis.service.constant.MessageCode;
import com.thesis.service.model.user.NotificationTable;
import com.thesis.service.repository.user.NotificationRepository;
import com.thesis.service.utils.ContextAccessor;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimerNotificationService {

  private final NotificationRepository notificationRepository;
  private final MessageSourceService messageSourceService;

  private static Timer registerTopicEndTimer = new Timer();

  @AllArgsConstructor
  public static class NotificationTask extends TimerTask {

    Supplier<List<NotificationTable>> findNotification = () -> List.of();

    @Override
    public void run() {
      ContextAccessor.getBean(NotificationRepository.class).saveAll(findNotification.get());
    }
  }

  public void notifyRegisterTopicEnd(Date time) {
    var timelineViewTag = messageSourceService.dashboardTag(
        messageSourceService.getMessage(MessageCode.Timer.TIMELINE_VIEW));
    var studentMessage = messageSourceService.getMessage(
        MessageCode.Timer.REGISTER_TOPIC_END,
        messageSourceService.topicExecuteTag(),
        timelineViewTag);
    var teacherMessage = messageSourceService.getMessage(
        MessageCode.Timer.REGISTER_TOPIC_END,
        messageSourceService.topicGuideTag(),
        timelineViewTag);
    var task = new TimerTask() {
      @Override
      public void run() {
        notificationRepository.notifyUserHasTopicInCurrentSemester(studentMessage, teacherMessage);
      }
    };
    registerTopicEndTimer = new Timer();
    registerTopicEndTimer.schedule(task, time);
  }

}
